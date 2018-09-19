package com.xskr.onk_v2.message;

import com.xskr.onk_v2.entity.ClientAction;

public class HallMessage extends XskrMessage {
    public HallMessage(String message, ClientAction action, Object data) {
        super(message, action, data);
    }
}
