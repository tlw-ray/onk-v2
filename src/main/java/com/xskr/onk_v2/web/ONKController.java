//package com.xskr.onk_v2.web;
//
//import com.xskr.onk_v2.entity.Room;
//import com.xskr.onk_v2.scene.ButtonType;
//import com.xskr.onk_v2.scene.HallScene;
//import com.xskr.onk_v2.scene.XskrMessage;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.*;
//
////房间的回收
//@RestController
//public class ONKController {
//
//    private Logger logger = LoggerFactory.getLogger(getClass());
//
//    HallScene hallScene = new HallScene();
//
//    @Autowired
//    private SimpMessagingTemplate simpMessagingTemplate;
//
//    @RequestMapping("/{buttonTypeString}/{buttonValue}")
//    public void pick(@PathVariable int buttonValue, @PathVariable String buttonTypeString, @PathVariable int buttonID){
////        Room room = idRoomMap.get(roomID);
//        ButtonType buttonType = ButtonType.valueOf(buttonTypeString);
////        String userName = getCurrentUserName();
////        room.getScene().pick(userName, buttonType, buttonID, null);
//        XskrMessage xskrMessage = hallScene.pick(getCurrentUserName(), buttonType, buttonValue, null);
//
//    }
//
//    private void sendQueueMessage(int roomID, XskrMessage message){
//        String roomWebSocketQueue = "/queue/" + roomID;
//        System.out.println(roomWebSocketQueue + '\t' + message);
//        simpMessagingTemplate.convertAndSendToUser(getCurrentUserName(), roomWebSocketQueue, message);
//    }
//
//    private void sendTopicMessage(int roomID, XskrMessage message){
//        String roomWebSocketTopic = "/topic/" + roomID;
//        System.out.println(roomWebSocketTopic + "\t" + message);
//        simpMessagingTemplate.convertAndSend(roomWebSocketTopic, message);
//    }
//
//
//}
