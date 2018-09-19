package com.xskr.onk_v2.web;

import com.xskr.onk_v2.scene.XskrMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class HallController {
    @Autowired
    RoomService roomService;
    @RequestMapping(path = "/hall/onk/createRoom", method = RequestMethod.POST)
    public XskrMessage createOnkRoom(){
        String userName = WebUtil.getCurrentUserName();
        return roomService.createRoom(userName);
    }

    @RequestMapping("/hall/onk/listOnkRoom")
    public XskrMessage listOnkRooms(){
        return roomService.listRoom();
    }

    @RequestMapping("/hall/onk/join/{roomID}")
    public void join(@PathVariable int id){
        return roomService.join(id);
    }
}
