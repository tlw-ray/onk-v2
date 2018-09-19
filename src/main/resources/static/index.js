//WebSocket 连接与订阅
var hallSockJS
var roomSockJS
var playerSockJS
var hallStompTopic
var roomStompTopic
var playerStompQueue
$(document).ready(function(){
    //建立WebSocket端点
    hallSockJS = new SockJS('/endpoint/onk/hall')
    roomSockJS = new SockJS('/endpoint/onk/room')
    playerSockJS = new SockJS('/endpoint/onk/player')
    subscribePlayerQueue()
    // subscribeRoomTopic()
    subscribeHallTopic()
    //登录进入该页面后进入大厅
    // enterHall()

    $('#create_and_join_room_button').click(function(){
        ajaxGet('/hall/createAndJoinRoom/onk')
    })
})

//进入大厅
function enterHall(){
    ajaxGet('/hall/listRoom/onk')
    //订阅大厅的topic
    $('#room_div').hide()
    $('#hall_div').show()
}

//进入房间
function enterRoom(room){

}

//订阅大厅公共消息
function subscribeHallTopic(){
    hallStompTopic = Stomp.over(hallSockJS)
    hallStompTopic.connect({}, function(frame){
        console.log(frame)
        hallStompTopic.subscribe('/topic/hall', function(message){
            var xskrMessage = JSON.parse(message.body)
            if(xskrMessage.action == 'LIST_ROOM'){
                //刚进入大厅或有玩家创建了新的房间，刷新可进入的房间
                $('#join_room_content_div').html('')
                for(var idx in xskrMessage.data){
                    var room = xskrMessage.data[idx]
                    var button = '<button>房间' + room.id + '</button>'
                    $('#join_room_content_div').append('')
                }
            }else{
                console.error('Hall topic, unsupported action: ' + xskrMessage.action)
            }
        })
    })
}

function subscribeRoomTopic(){
    roomStompTopic = Stomp.over(playerSockJS)
    roomStompTopic.connect({}, function(frame){
        roomStompTopic.subscribe('/user/queue', function(message){
            var xskrMessage = JSON.parse(message.body)
            if(xskrMessage.action == 'LIST_ROOM'){
                //刚进入大厅或有玩家创建了新的房间，刷新可进入的房间
                listJoinRoom(xskrMessage)
            }else{
                console.error('Player queue, unsupported action: ' + xskrMessage.action)
            }
        })
    })

}

function subscribePlayerQueue(){
    playerStompQueue = Stomp.over(playerSockJS)
    playerStompQueue.connect({}, function(frame){
        console.log(frame)
        playerStompQueue.subscribe('/user/queue', function(message){
            var xskrMessage = JSON.parse(message.body)
            if(xskrMessage.action == 'LIST_ROOM'){
                //刚进入大厅或有玩家创建了新的房间，刷新可进入的房间
                listRoom(xskrMessage)
            }else if(xskrMessage.action == 'JOIN_ROOM'){
                joinRoom(xskrMessage)
            }else{
                console.error('Player queue, unsupported action: ' + xskrMessage.action)
            }
        })
        //订阅完毕后拉取房间列表
        $.ajax({
            url:'/hall/listRoom/onk',
            type: 'GET',
            Accept : "application/json",
            contentType: "application/json",
            success: function(){
                //订阅大厅的topic
                $('#room_div').hide()
                $('#hall_div').show()
            },
            error:function(ex){
                console.log(ex);
            }
        })
    })
}

function listRoom(xskrMessage){
    $('#join_room_content_div').html('')
    for(var idx in xskrMessage.data){
        var room = xskrMessage.data[idx]
        var url = '/onk/join/' + room.id
        var button = $('<button/>', {
            text: '房间' + room.id,
            id: 'room_button_'+room.id,
            click: function () { ajaxGet(url) }
        });
        $('#join_room_content_div').append(button)
    }
}

function joinRoom(room){
    console.log('join join room !!' + room)
    $('#hall_div').hide()
    $('#room_div').show()
}

function exitRoom(){
    ajaxGet('/onk/leave')
}

//不再订阅
function unsubscribe(stomp){
    if(stomp != null){
        stomp.disconnect()
    }
}

//调用没有返回值的AJAX的GET方法
function ajaxGet(url){
    $.ajax({
        url: url,
        type: 'GET',
        Accept : "application/json",
        contentType: "application/json",
        success: function(data){
            console.log('call: ' + url)
            return data
        },
        error:function(ex){
            console.log(ex)
        }
    });
}
