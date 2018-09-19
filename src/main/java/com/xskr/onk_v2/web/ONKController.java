//package com.xskr.onk_v2.web;
//
//import com.xskr.onk_v2.entity.Room;
//import com.xskr.onk_v2.status.ButtonType;
//import com.xskr.onk_v2.status.HallScene;
//import com.xskr.onk_v2.message.XskrMessage;
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
////        room.getStatus().pick(userName, buttonType, buttonID, null);
//        XskrMessage xskrMessage = hallScene.pick(getCurrentUserName(), buttonType, buttonValue, null);
//
//    }
//
//
//
//}
