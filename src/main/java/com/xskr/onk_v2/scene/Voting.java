package com.xskr.onk_v2.scene;

import com.xskr.onk_v2.Scene;
import com.xskr.onk_v2.entity.Room;

public class Voting extends Scene {

    public Voting(Room room){
        super(room);
    }

    public Preparing nextScene(){
        return new Preparing(room);
    }

    @Override
    public XskrMessage pick(String userName, ButtonType buttonType, int id, Object param) {
        return null;
    }
}
