package com.xskr.onk_v2;

import com.xskr.onk_v2.scene.ButtonType;
import com.xskr.onk_v2.scene.XskrMessage;

public interface Scene {
    XskrMessage pick(String userName, ButtonType buttonType, int id, Object param);
}
