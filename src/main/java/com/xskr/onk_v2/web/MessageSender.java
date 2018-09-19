package com.xskr.onk_v2.web;

import com.xskr.onk_v2.message.HallMessage;
import com.xskr.onk_v2.message.PlayerMessage;
import com.xskr.onk_v2.message.RoomMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    public void sendMessage(PlayerMessage playerMessage){
        String userMessageQueue = "/queue";
        simpMessagingTemplate.convertAndSendToUser(playerMessage.getPlayerName(), userMessageQueue, playerMessage);
    }

    public void sendMessage(RoomMessage roomMessage){
        String roomMessageTopic = "/topic/" + roomMessage.getRoomID();
        simpMessagingTemplate.convertAndSend(roomMessageTopic, roomMessage);
    }

    public void sendMessage(HallMessage hallMessage){
        String hallMessageTopic = "/topic/hall";
        simpMessagingTemplate.convertAndSend(hallMessageTopic, hallMessage);
    }
}
