var stompClient = null;
var check = 0;
var name = null;
var messageCounter = 1;

function setBackground() {
	messageCounter += 1;
	if (messageCounter % 2 == 0) {
		$("#chat").css({backgroundColor: "#DCDCDC"});
	}
}

function setConnected(connected) {
    if (connected) {
        $("#username-form").show();
    	$("#name").focus()
    	$("#error").hide();
    }
    else {
    	$("#error").show();
       	$("#conversation").hide();
		$("#message-form").hide();
		$("#username-form").hide();
    }
}

function connect() {
    var socket = new SockJS("/gs-guide-websocket");
    
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
    	if (window.check){
    		window.clearInterval(window.check);
   			window.check=0;
    	};
        console.log("Connected: " + frame);
        stompClient.subscribe("/topic/chat", function (message) {
            showMessage(JSON.parse(message.body).content);
        });
        stompClient.subscribe("/topic/newusers", function (newUser) {
            showNotification(JSON.parse(newUser.body).content);
    	});
        
    	socket.onclose = function() {
    		$("#error").html("<div class='alert alert-danger'>Woops; something went wrong with the connection</div>");
    		if (!window.check) {
	    		window.check = setInterval(function(){connect()}, 5000);    	
    		};
    		setConnected(false);
    	};
    	setConnected(true);
    });
}

function setUsername() {
    stompClient.send("/app/username", {}, JSON.stringify({"content": $("#name").val()}));
    window.name = $("#name").val();
    $("#username-form").hide();
}

function sendMessage() {
    stompClient.send("/app/chat", {}, JSON.stringify({"content": window.name + ": " + $("#message").val()}));
    $("#message-form")[0].reset();
}

function showMessage(message) {
	setBackground();
    $("#chat").append("<tr><td>" + message + "</td></tr>");
}

function showNotification(message) {
	setBackground();
    $("#chat").append("<tr><td>" + message + "</td></tr>");
}

$(function () {	
	setConnected(false);
	connect();
    $("form").on("submit", function (e) {
        e.preventDefault();
    });
    $("#send-name").click(function() { 
     	$("#conversation").show();  
     	$("#message-form").show();	
     	$("#message").focus()
    	setUsername(); 
    	});
    	
    $("#send-message").click(function() { 
    	sendMessage(); 
    	});
});

