package com.xskr.onk_v2.entity;

import com.xskr.onk_v2.Player;
import com.xskr.onk_v2.Scene;
import com.xskr.onk_v2.scene.Preparing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.*;

public class Room {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static final int TABLE_DECK_THICKNESS = 3;

    // 房间号
    private int id;
    // 用于客户端识别当前登录用户, TODO 应由框架引入
    private Player currentPlayer;
    // 该房间的创建者
    private String owner;



    // 该房间开启使用的卡牌
    private Map<Card, Boolean> cardUsingMap = new HashMap(Card.values().length);
    // 发牌后剩余的桌面3张牌垛
    private Card[] desktopCards = new Card[TABLE_DECK_THICKNESS];
    // 所有进入房间的玩家(包含已经有座位和还没有座位的)
    private Set<OnkPlayer> players = new TreeSet();


    // 游戏所处的状态
    private Scene scene = new Preparing(this);


    // 用于发送WebSocket信息
    protected SimpMessagingTemplate simpMessagingTemplate;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Map<Card, Boolean> getCardUsingMap() {
        return cardUsingMap;
    }

    public void setCardUsingMap(Map<Card, Boolean> cardUsingMap) {
        this.cardUsingMap = cardUsingMap;
    }

    public Card[] getDesktopCards() {
        return desktopCards;
    }

    public void setDesktopCards(Card[] desktopCards) {
        this.desktopCards = desktopCards;
    }

    public Set<OnkPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(Set<OnkPlayer> players) {
        this.players = players;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public SimpMessagingTemplate getSimpMessagingTemplate() {
        return simpMessagingTemplate;
    }

    public void setSimpMessagingTemplate(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
