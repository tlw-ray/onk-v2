package com.xskr.onk_v2.web.room;

import com.xskr.onk_v2.entity.ClientAction;
import com.xskr.onk_v2.entity.OnkPlayer;
import com.xskr.onk_v2.entity.Room;
import com.xskr.onk_v2.message.PlayerMessage;
import com.xskr.onk_v2.message.RoomMessage;
import com.xskr.onk_v2.web.MessageSender;
import com.xskr.onk_v2.web.WebUtil;
import com.xskr.onk_v2.web.player.OnkPlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/onk")
public class OnkRoomController {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    OnkRoomService roomService;
    @Autowired
    OnkPlayerService playerService;
    @Autowired
    MessageSender messageSender;

    /**
     * 玩家进入房间
     * 推送该消息到该房间内所有玩家处
     * @param roomID
     */
    @RequestMapping("/join/{roomID}")
    public void joinRoom(@PathVariable int roomID){
        String userName = WebUtil.getCurrentUserName();
        Room room = roomService.joinRoom(userName, roomID);
        RoomMessage roomMessage = new RoomMessage(roomID, "", ClientAction.REFRESH_PLAYERS_INFO_ACTION, room);
        messageSender.sendMessage(roomMessage);
    }

    /**
     * 玩家离开房间的信息
     * 推送该消息到所有该房间的玩家处
     * 推送该消息到该玩家处
     */
    @RequestMapping("/leave")
    public void leaveRoom(){
        String playerName = WebUtil.getCurrentUserName();
        OnkPlayer onkPlayer = playerService.findPlayerByName(playerName);
        if(onkPlayer != null) {
            Room room = onkPlayer.getRoom();
            if(room != null) {
                int roomID = room.getId();
                Room leavedRoom = roomService.leaveRoom(playerName, roomID);
                RoomMessage roomMessage = new RoomMessage(roomID, "", ClientAction.REFRESH_PLAYERS_INFO_ACTION, leavedRoom);
                messageSender.sendMessage(roomMessage);
                PlayerMessage playerMessage = new PlayerMessage(playerName, "", ClientAction.LIST_ROOM, roomService.listRoom());
                messageSender.sendMessage(playerMessage);
            }else{
                logger.error("leaveRoom() 异常: 玩家{}要退出的房间不存在。", playerName);
            }
        }
    }
}
