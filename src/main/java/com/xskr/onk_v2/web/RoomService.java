package com.xskr.onk_v2.web;

import com.xskr.onk_v2.entity.ClientAction;
import com.xskr.onk_v2.entity.Room;
import com.xskr.onk_v2.scene.PlayerMessage;
import com.xskr.onk_v2.scene.RoomMessage;
import com.xskr.onk_v2.scene.XskrMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

public class RoomService {

    private static AtomicInteger RoomID_Generator = new AtomicInteger();
    private Map<Integer, Room> idRoomMap = Collections.synchronizedMap(new TreeMap());
    private Map<String, Room> playerNameRoomMap = new HashMap();
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    public Room createRoom(String userName){
        int roomID = RoomID_Generator.incrementAndGet();
        Room room = new Room();
        room.setId(roomID);
        room.setOwner(userName);
        idRoomMap.put(roomID, room);
    }

    public XskrMessage listRoom(String userName){
        return new PlayerMessage(userName,"", ClientAction.LIST_ROOM, idRoomMap.values());
    }

    //有人加入房间，消息发送给该房间内的所有人
    public XskrMessage joinRoom(String userName, int roomID){
        Room room = idRoomMap.get(roomID);

        return new RoomMessage(roomID, "", ClientAction.REFRESH_PLAYERS_INFO_ACTION, room);
    }

    public XskrMessage exitRoom(String userName, int roomID){
        Room room = idRoomMap.get(roomID);

        return new RoomMessage(roomID, "", ClientAction.REFRESH_PLAYERS_INFO_ACTION, room);
    }

}
