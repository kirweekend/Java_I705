package hello;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

	@MessageMapping("/chat")
	@SendTo("/topic/chat")
	public Message addMess(Message message) throws Exception {
		Thread.sleep(100);
		return message;
	}

	@MessageMapping("/username")
	@SendTo("/topic/newusers")
	public Message setUser(Message message) throws Exception {
		Thread.sleep(100); // simulated delay
		return new Message(message.getContent() + " has joined the conversation");
	}

}
