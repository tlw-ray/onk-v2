package com.xskr.onk_v2;

import com.xskr.onk_v2.status.ButtonType;
import com.xskr.onk_v2.message.XskrMessage;

public interface Status {
    XskrMessage pick(String userName, ButtonType buttonType, int id, Object param);
}
