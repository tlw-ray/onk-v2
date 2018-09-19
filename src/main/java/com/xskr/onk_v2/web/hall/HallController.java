package com.xskr.onk_v2.web.hall;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xskr.onk_v2.entity.ClientAction;
import com.xskr.onk_v2.entity.Room;
import com.xskr.onk_v2.message.HallMessage;
import com.xskr.onk_v2.message.PlayerMessage;
import com.xskr.onk_v2.web.MessageSender;
import com.xskr.onk_v2.web.room.OnkRoomService;
import com.xskr.onk_v2.web.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/hall")
public class HallController {

    @Autowired
    OnkRoomService roomService;

    @Autowired
    MessageSender messageSender;

    /**
     * 玩家创建了房间
     * 推送该消息到所有订阅大厅Topic的用户处
     * 推送加入房间信息到该建立房间的玩家处，触发其进入房间
     */
    @RequestMapping(path = "/createAndJoinRoom/onk")
    public void createAndJoinOnkRoom(){
        System.out.println("createAndJoinOnkRoom");
        String playerName = WebUtil.getCurrentUserName();
        Room room = roomService.createAndJoinRoom(playerName);
        PlayerMessage playerMessage = new PlayerMessage(playerName, "", ClientAction.JOIN_ROOM, room);
        messageSender.sendMessage(playerMessage);
        HallMessage hallMessage = new HallMessage("", ClientAction.LIST_ROOM, roomService.listRoom());
        messageSender.sendMessage(hallMessage);
    }

    /**
     * 玩家进入大厅后触发查询所有已有房间的操作
     * 推送该消息到该玩家处
     */
    @RequestMapping("/listRoom/onk")
    public void listOnkRooms(){
        System.out.println("listOnkRooms");
        String playerName = WebUtil.getCurrentUserName();
        PlayerMessage playerMessage = new PlayerMessage(playerName,"", ClientAction.LIST_ROOM, roomService.listRoom());
        messageSender.sendMessage(playerMessage);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(System.out, roomService.listRoom());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @Scheduled(fixedRate = 10000)
//    public void send(){
//        HallMessage hallMessage = new HallMessage("xxx", null, null);
//        messageSender.sendMessage(hallMessage);
//        PlayerMessage playerMessage = new PlayerMessage("tlw", "yyyy", null, null);
//        messageSender.sendMessage(playerMessage);
//        System.out.println("send...");
//    }
}
