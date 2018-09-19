package com.xskr.onk_v2.scene;

import com.xskr.onk_v2.Scene;
import com.xskr.onk_v2.entity.Room;

public abstract class RoomScene implements Scene {
    protected Room room;

    public RoomScene(Room room){
        this.room = room;
    }
}
