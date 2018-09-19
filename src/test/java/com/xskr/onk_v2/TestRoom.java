package com.xskr.onk_v2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xskr.onk_v2.entity.Room;
import com.xskr.onk_v2.web.player.OnkPlayerService;
import com.xskr.onk_v2.web.room.OnkRoomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRoom {
    @Autowired
    OnkRoomService roomService;
    @Test
    public void testRoom() throws IOException {
        Room room = roomService.createAndJoinRoom("aaa");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(System.out, room);
    }
}
