package com.xskr.onk_v2.scene;

import com.xskr.onk_v2.Scene;
import com.xskr.onk_v2.entity.Room;

public class Preparing extends Scene {
    public Preparing(Room room){
        super(room);
    }

    public Activing nextScene() {
        return new Activing(room);
    }

    @Override
    public XskrMessage pick(String userName, ButtonType buttonType, int id, Object param) {
        return null;
    }
}
