package com.xskr.onk_v2.message;

import com.xskr.onk_v2.entity.ClientAction;

public class PlayerMessage extends XskrMessage {

    String playerName;

    public PlayerMessage(String playerName, String message, ClientAction action, Object data) {
        super(message, action, data);
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
