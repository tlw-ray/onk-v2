package com.xskr.onk_v2.web.room;

import com.xskr.onk_v2.entity.Card;
import com.xskr.onk_v2.entity.OnkPlayer;
import com.xskr.onk_v2.entity.Room;
import com.xskr.onk_v2.status.OnkStatus;
import com.xskr.onk_v2.web.player.OnkPlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrepareService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    OnkPlayerService onkPlayerService;

    /**
     * 玩家设定准备状态
     * 如果游戏在Preparing状态，玩家可以任意改变他的准备状态，其他状态玩家无法取消其准备状态
     * 所有玩家都已准备，游戏进入Activity状态
     * @param playerName
     * @param ready
     */
    public Room setReady(String playerName, boolean ready){
        OnkPlayer onkPlayer = onkPlayerService.findPlayerByName(playerName);
        if(onkPlayer != null){
            Room room = onkPlayer.getRoom();
            if(room.getStatus() == OnkStatus.PREPARING){
                onkPlayer.setReady(ready);
                //如果玩家是准备开始，检查该房间内的所有玩家是否已经准备
                if(ready == true){
                    boolean allReady = true;
                    for(OnkPlayer anyPlayer:room.getAllPlayers()){
                        if(!anyPlayer.isReady()){
                            allReady = false;
                            break;
                        }
                    }

                    if(allReady && room.getSeatPlayers().size() == room.getSeatCount()){
                        //如果玩家数量已达设定，并且所有玩家均已进入准备状态则开始新的一局游戏
//                        newGame(room);
                    }else{
                        //do nothing
                    }
                }else{
                    //do nothing
                }
                return room;
            }else{
                //正在行动或投票过程中，无法设定准备状态
            }
        }else{
            logger.error("setReady(playerName={}, ready={}) 异常: 玩家没有在任何房间，无法设定准备状态", playerName, ready);
        }
        return null;
    }

    /**
     * 玩家通过点击更换座位
     * 如果玩家点击到一个空位子，则将设定玩家的位子到该空位
     * 如果玩家点击到一个位子已经有人，则什么也不会发生
     * 如果玩家点击到自己所在的位子，把玩家切换到未就坐的玩家队列，并清空玩家的座位信息
     * @param playerName
     * @param sit
     */
    public void sit(String playerName, int sit){

    }

    /**
     * 房主在准备期间选择房间的卡牌配置
     * 点选或反选一张卡牌
     * @param card
     */
    private void pickCard(String playerName, Card card){

    }

//    /**
//     * 所有的玩家都已准备，开启一局新游戏
//     * @param room
//     */
//    private void newGame(Room room){
//        //标记进入游戏状态
//        room.setStatus(OnkStatus.ACTING);
//
//        //洗牌
//        Card[] cards = room.getUsingCards();
//        Deck deck = new Deck(cards);
//
//        //检查玩家是否都已经就坐, 座位号是否符合逻辑 TODO 如果经过严格的自动化测试这里就不需要检查
//        for(int i=1; i<=room.getSeatCount(); i++){
//            if(room.getPlayerBySeat(i) == null){
//                logger.error("newGame(room) 异常: 缺少{}号位上的玩家，游戏无法开始。");
//                return;
//            }
//        }
//
//        //清空上一局所有角色的操作状态
//        singleWolfCheckDeck = null;
//        seerCheckDeck1 = null;
//        seerCheckDeck2 = null;
//        seerCheckPlayer = null;
//        robberSnatchSeat = null;
//        troublemakerExchangeSeat1 = null;
//        troublemakerExchangeSeat2 = null;
//        drunkExchangeDeck = null;
//        //清空上一局所有玩家的身份与投票状态
//        for(Player player:players){
//            player.setCard(null);
//        }
//        //清空每个玩家身上的被投票状态
//        clearVote();
//
//        //洗牌
//        deck.shuffle(500);
//
//        //为所有人发牌，并建立牌到玩家的索引
//        initializeCardPlayerMap.clear();
//        for(Player player:players){
//            Card card = deck.deal();
//            player.setCard(card);
//            initializeCardPlayerMap.put(card, player);
//        }
//
//        //建立桌面剩余的牌垛
//        tableDeck.put(1, deck.deal());
//        tableDeck.put(2, deck.deal());
//        tableDeck.put(3, deck.deal());
//
//        logger.debug("playerAction");
//        //需要主动行动的玩家
//        Player singleWolf = getSingleWolf();
//        Player seer = initializeCardPlayerMap.get(Card.SEER);
//        Player robber = initializeCardPlayerMap.get(Card.ROBBER);
//        Player troublemaker = initializeCardPlayerMap.get(Card.TROUBLEMAKER);
//        Player drunk = initializeCardPlayerMap.get(Card.DRUNK);
//
//        //发牌结束后根据身份为每个玩家发送行动提示信息
//        Map<Player, XskrMessage> playerXskrMessageMap = new HashMap();
//        boolean directVote = true;
//        for(Map.Entry<Card, Player> entry: initializeCardPlayerMap.entrySet()){
//            Player player = entry.getValue();
//            if(player != null) {
//                String message;
//                ClientAction clientAction = null;
//                if(player == singleWolf){
//                    message = String.format("请勾选牌1、牌2、牌3中的一张，点击“行动”，来查看桌面牌垛中对应的卡牌。");
//                    clientAction = ClientAction.SINGLE_WOLF_ACTION;
//                    directVote = false;
//                }else if (player == seer) {
//                    message = "请勾选牌1、牌2、牌3中的任意两个来查看的桌面牌垛中对应的卡牌，或者勾选一位玩家，点击“行动”来查看其身份。";
//                    clientAction = ClientAction.SEER_ACTION;
//                    directVote = false;
//                }else if(player == robber){
//                    message = "请输勾选任意玩家，点击“行动”查阅其卡牌并交换身份。";
//                    clientAction = ClientAction.ROBBER_ACTION;
//                    directVote = false;
//                }else if(player == troublemaker){
//                    message = "请勾选除您之外两个玩家，点击“行动”，交换他们的身份。";
//                    clientAction = ClientAction.TROUBLEMAKER_ACTION;
//                    directVote = false;
//                }else if(player == drunk){
//                    message = "请勾选牌1、牌2、牌3中任意一张，点击“行动”与之交换身份。";
//                    clientAction = ClientAction.DRUNK_ACTION;
//                    directVote = false;
//                }else{
//                    message = "所有玩家行动完成后，系统会给出下一步的信息。";
//                }
//                //预备玩家身份和操作信息
//                XskrMessage xskrMessage1 = new XskrMessage(String.format("您的初始身份是%s。<br>" + message, getDisplayName(player.getCard())), clientAction, null);
//                playerXskrMessageMap.put(player, xskrMessage1);
//            }
//        }
//        //向玩家发送身份和操作提示信息
//        for(Map.Entry<Player, XskrMessage> entry:playerXskrMessageMap.entrySet()){
//            Player player = entry.getKey();
//            XskrMessage xskrMessage1 = entry.getValue();
//            sendMessage(player, xskrMessage1);
//            keepKeyMessage(player, xskrMessage1);
//        }
//        if(directVote){
//            //没有任何玩家需要行动，直接进入投票阶段
//            try {
//                Thread.sleep(10000);
//            }catch(Exception e){
//                e.printStackTrace();
//            }
//            TimerTask timerTask = new TimerTask() {
//                @Override
//                public void run() {
//                    attemptNightAction();
//                }
//            };
//            //这里等待十秒后触发，多线程是否有问题
//            //TODO 无论是否可以直接进入投票阶段，都应至少等待10秒钟
//            Timer timer = new Timer();
//            timer.schedule(timerTask, 10000);
//        }else{
//            // do nothing
//        }
//    }

}
