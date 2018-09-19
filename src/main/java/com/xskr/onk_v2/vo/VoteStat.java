package com.xskr.onk_v2.vo;

import com.xskr.onk_v2.entity.Card;
import com.xskr.onk_v2.entity.OnkPlayer;

import java.util.Set;

//投票统计结果
public class VoteStat {
    //是否有狼
    boolean hasWolfInPlayers;
    //是否有猎人
    boolean hasHunterInPlayers;
    //最大得票数
    int maxVoteCount;
    //获得最大得票数的玩家集合
    Set<OnkPlayer> votedOnkPlayer;

    public VoteStat(boolean hasWolfInPlayers, boolean hasHunterInPlayers, int maxVoteCount, Set<OnkPlayer> votedOnkPlayer) {
        this.hasWolfInPlayers = hasWolfInPlayers;
        this.hasHunterInPlayers = hasHunterInPlayers;
        this.maxVoteCount = maxVoteCount;
        this.votedOnkPlayer = votedOnkPlayer;
    }

    public boolean hasWolfInPlayers() {
        return hasWolfInPlayers;
    }

    public boolean onlyVotedTanner() {
        return votedOnkPlayer.size() == 1 && votedOnkPlayer.iterator().next().getCard() == Card.TANNER;
    }

    public boolean voted(Card card){
        for(OnkPlayer onkPlayer : votedOnkPlayer){
            if(onkPlayer.getCard() == card){
                return true;
            }
        }
        return false;
    }

    public boolean hasHunterInPlayers() {
        return hasHunterInPlayers;
    }

    public int getMaxVoteCount() {
        return maxVoteCount;
    }

    public Set<OnkPlayer> getVotedOnkPlayer() {
        return votedOnkPlayer;
    }
}
