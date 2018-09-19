package com.xskr.onk_v2.scene;

import com.xskr.onk_v2.Scene;
import com.xskr.onk_v2.entity.Room;

public class Activing extends Scene {
    public Activing(Room room){
        super(room);
    }

    @Override
    public XskrMessage pick(String userName, ButtonType buttonType, int id, Object param) {

        return null;
    }

    public Voting nextScene(){
        return new Voting(room);
    }
}
