package com.xskr.onk_v2.entity;

import com.xskr.onk_v2.status.OnkStatus;

import java.util.*;

public class Room {

    public static final int TABLE_DECK_THICKNESS = 3;

    // 房间号
    private int id;
    // 用于客户端识别当前登录用户的座位, TODO 应由框架引入
    private OnkPlayer currentPlayer;
    // 该房间的创建者
    private String owner;

    // 该房间开启使用的卡牌
    private Map<Card, Boolean> cardUsingMap = new HashMap(Card.values().length);
    // 发牌后剩余的桌面3张牌垛, 序号从1开始
    private Map<Integer, Card> desktopCards = new TreeMap();
    // 所有进入房间的玩家(包含已经有座位和还没有座位的)，座位从1开始排
    private Set<OnkPlayer> allPlayers = new TreeSet();

    // 游戏所处的状态
    private OnkStatus status = OnkStatus.PREPARING;

    /**
     * 获得当前房间所有被房主选中使用的卡牌
     * @return
     */
    public Card[] getUsingCards(){
        List<Card> usingCards = new ArrayList();
        for(Map.Entry<Card, Boolean> entry: getCardUsingMap().entrySet()){
            Card card = entry.getKey();
            if(entry.getValue()){
                usingCards.add(card);
            }
        }
        return usingCards.toArray(new Card[0]);
    }

    /**
     * 获得当前房间的座位数
     */
    public int getSeatCount(){
        return getUsingCards().length - TABLE_DECK_THICKNESS;
    }

    /**
     * 获得当前房间有座位的玩家集合
     */
    public Set<OnkPlayer> getSeatPlayers(){
        Set<OnkPlayer> seatPlayers = new TreeSet();
        for(OnkPlayer onkPlayer:getAllPlayers()){
            if(onkPlayer.getSeat() > 0){
                seatPlayers.add(onkPlayer);
            }
        }
        return seatPlayers;
    }

    /**
     * 获得当前房间内观看者玩家集合
     * @return
     */
    public Set<OnkPlayer> getObserverPlayers(){
        Set<OnkPlayer> observerPlayers = new TreeSet();
        for(OnkPlayer onkPlayer:getAllPlayers()){
            if(onkPlayer.getSeat() < 0){
                observerPlayers.add(onkPlayer);
            }
        }
        return observerPlayers;
    }

    /**
     * 根据玩家的名称从房间内找到
     * @param playerName
     * @return
     */
    public OnkPlayer getPlayerByName(String playerName){
        for(OnkPlayer onkPlayer:getAllPlayers()){
            if(onkPlayer.getName().equals(playerName)){
                return onkPlayer;
            }
        }
        return null;
    }

    /**
     * 根据玩家的座位从房间内找到
     * @param seat
     * @return
     */
    public OnkPlayer getPlayerBySeat(int seat){
        for(OnkPlayer onkPlayer:getAllPlayers()){
            if(onkPlayer.getSeat() == seat){
                return onkPlayer;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OnkPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(OnkPlayer currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Map<Card, Boolean> getCardUsingMap() {
        return cardUsingMap;
    }

    public Map<Integer, Card> getDesktopCards() {
        return desktopCards;
    }

    public Set<OnkPlayer> getAllPlayers() {
        return allPlayers;
    }

    public OnkStatus getStatus() {
        return status;
    }

    public void setStatus(OnkStatus status) {
        this.status = status;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
