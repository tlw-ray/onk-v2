package com.xskr.onk_v2.web.room;

import com.xskr.onk_v2.entity.OnkPlayer;
import com.xskr.onk_v2.entity.Room;
import com.xskr.onk_v2.web.player.OnkPlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
@Service
public class OnkRoomService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static AtomicInteger RoomID_Generator = new AtomicInteger();

    private Map<Integer, Room> idRoomMap = Collections.synchronizedMap(new TreeMap());
    private Map<String, Room> playerNameRoomMap = Collections.synchronizedMap(new HashMap());

    @Autowired
    private OnkPlayerService playerService;

    /**
     * 创建新的房间并加入
     * @param playerName 创建房间的玩家用户名
     * @return 被创建且加入的房间实例
     */
    public Room createAndJoinRoom(String playerName){
        //create room
        int roomID = RoomID_Generator.incrementAndGet();
        Room room = new Room();
        room.setId(roomID);
        room.setOwner(playerName);
        idRoomMap.put(roomID, room);
        //join room
        return joinRoom(playerName, roomID);
    }

    public Collection<Room> listRoom(){
        return idRoomMap.values();
    }

    //有人加入房间
    public Room joinRoom(String playerName, int roomID){
        //建立房间与玩家之间的双向关联
        OnkPlayer onkPlayer = playerService.findOrCreatePlayerByName(playerName);
        Room room = idRoomMap.get(roomID);
        room.getAllPlayers().add(onkPlayer);
        onkPlayer.setRoom(room);
        return room;
    }

    //有玩家离开房间
    public Room leaveRoom(String userName, int roomID){
        Room room = idRoomMap.get(roomID);
        OnkPlayer onkPlayer = findPlayer(userName, roomID);
        if(onkPlayer != null){
            //如果该玩家是在该房间则将其移除
            room.getAllPlayers().remove(onkPlayer);
            onkPlayer.setRoom(null);
            if(room.getAllPlayers().size() > 0){
                //如果退出的玩家离开后该房间仍然有其他玩家，则检查此玩家是房主
                if(room.getOwner().equals(onkPlayer.getName())){
                    //如果退出的玩家是房主，则将房主转让到其他玩家
                    room.setOwner(room.getAllPlayers().iterator().next().getName());
                }else{
                    //如果退出玩家不是房主则什么也不用做
                }
            }else{
                //如果房间已经没人了就关闭该房间
                idRoomMap.remove(roomID);
            }
        }else{
            //该玩家并不在这个房间，系统出现了错误
            logger.error("leaveRoom(userName={}, roomID={})异常: 玩家并不在该房间内。", userName, roomID);
        }
        return room;
    }

    private OnkPlayer findPlayer(String userName, int roomID){
        Room room = idRoomMap.get(roomID);
        for(OnkPlayer player: room.getAllPlayers()){
            if(player.getName().equals(userName)){
                return player;
            }
        }
        return null;
    }

}
