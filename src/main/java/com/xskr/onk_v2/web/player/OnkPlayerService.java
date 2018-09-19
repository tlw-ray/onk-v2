package com.xskr.onk_v2.web.player;

import com.xskr.onk_v2.entity.OnkPlayer;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

@Service
public class OnkPlayerService {

    private Map<String, OnkPlayer> namePlayerMap = Collections.synchronizedMap(new TreeMap());

    public OnkPlayer findOrCreatePlayerByName(String playerName){
        OnkPlayer onkPlayer = namePlayerMap.get(playerName);
        if(onkPlayer == null){
            onkPlayer = new OnkPlayer(playerName);
            namePlayerMap.put(playerName, onkPlayer);
        }
        return onkPlayer;
    }

    public OnkPlayer findPlayerByName(String playerName){
        return namePlayerMap.get(playerName);
    }

}
