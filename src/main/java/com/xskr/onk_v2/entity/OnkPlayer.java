package com.xskr.onk_v2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xskr.onk_v2.message.XskrMessage;

import java.util.ArrayList;
import java.util.List;

public class OnkPlayer implements Comparable<OnkPlayer>{

	private String name;
	private Card initializeCard;								//玩家初始卡牌信息
	private Card card;											//玩家当前卡牌信息
	private int seat;											//玩家座位信息
	private boolean ready;										//玩家准备信息
	private Integer voteSeat;									//该玩家投票到某个座位的玩家
	private int votedCount;										//该玩家被投票的次数
	private List<XskrMessage> keyMessages = new ArrayList();	//存放客户端的关键信息，供断线重连后查看
	private ClientAction lastAction;							//存放该玩家当前的行动信息，供断线重连后查看
	private transient Room room;								//玩家当前所在房间信息

	public OnkPlayer(String name) {
		super();
		this.name = name.trim();
	}

	public String getName() {
		return name;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public boolean isReady() {
		return ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public int getSeat() {
		return seat;
	}

	public void setSeat(int seat) {
		this.seat = seat;
	}

	public Integer getVoteSeat() {
		return voteSeat;
	}

	public void setVoteSeat(Integer voteSeat) {
		this.voteSeat = voteSeat;
	}

	public int getVotedCount() {
		return votedCount;
	}

	public void setVotedCount(int votedCount) {
		this.votedCount = votedCount;
	}

	public void beVote(){
		votedCount++;
	}

	public Card getInitializeCard() {
		return initializeCard;
	}

	public void setInitializeCard(Card initializeCard) {
		this.initializeCard = initializeCard;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<XskrMessage> getKeyMessages() {
		return keyMessages;
	}

	public void setKeyMessages(List<XskrMessage> keyMessages) {
		this.keyMessages = keyMessages;
	}

	public ClientAction getLastAction() {
		return lastAction;
	}

	public void setLastAction(ClientAction lastAction) {
		this.lastAction = lastAction;
	}

	@JsonIgnore
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	//	@Override
//	public boolean equals(Object o) {
//		if (this == o) return true;
//		if (o == null || getClass() != o.getClass()) return false;
//		OnkPlayer player = (OnkPlayer) o;
//		return Objects.equals(name, player.name);
//	}
//
//	@Override
//	public int hashCode() {
//		return Objects.hash(name);
//	}

	@Override
	public int compareTo(OnkPlayer o) {
		if(o == null){
			return 1;
		}else{
			return Integer.compare(getSeat(), o.getSeat());
		}
	}

    @Override
    public String toString() {
        return "OnkPlayer{" +
                "name='" + name + '\'' +
                ", card=" + card +
                ", seat=" + seat +
                ", voteSeat=" + voteSeat +
                ", votedCount=" + votedCount +
                '}';
    }
}
