package by.twister.web_app;

import by.twister.model.Message;
import by.twister.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/messages")
    public final String findAllMessages(Model model) {

        List<Message> messages = messageService.findAll();
        model.addAttribute("messagesAll", messages);

        return "messages";
    }

    @GetMapping("/message-edit")
    public final String goToEditMessage(Integer id, Model model) {

        Message message = messageService.findById(id);
        model.addAttribute("messageEdit", message);

        return "messages";
    }

    @GetMapping("/message")
    public final String goToAddMessage(Model model) {

        Message message = new Message();
        model.addAttribute("messageAdd", message);

        return "messages";
    }

    @PostMapping("/message")
    public final String addMessage(Message message) {
        messageService.add(message);
        return "messages";
    }

    @GetMapping
    public final String deleteMessage(Integer id) {
        messageService.delete(id);
        return "messages";
    }




}
