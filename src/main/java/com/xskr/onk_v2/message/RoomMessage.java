package com.xskr.onk_v2.message;

import com.xskr.onk_v2.entity.ClientAction;

public class RoomMessage extends XskrMessage{

    int roomID;

    public RoomMessage(int roomID, String message, ClientAction action, Object data) {
        super(message, action, data);
        this.roomID = roomID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }
}
