package ru.rosbank.javaschool.slack.rest;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import ru.rosbank.javaschool.slack.domain.Message;
import ru.rosbank.javaschool.slack.repository.MessageRepository;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("message")
public class RestMessageController {
    private final MessageRepository messageRepository;

    @Autowired
    public RestMessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping
    public List<Message> list() {
        return messageRepository.findAll();
    }

    @GetMapping("{id}")
    public Message getOne(@PathVariable Message message) {
        return message;
    }

    @PostMapping
    public Message create(@RequestBody Message message) {
        message.setCreationDate(LocalDateTime.now());
        return messageRepository.save(message);
    }

    @PutMapping("{id}")
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message
    ) {
        BeanUtils.copyProperties(message, messageFromDb, "id");

        return messageRepository.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        messageRepository.delete(message);
    }

    @MessageMapping("/changeMessage")
    @SendTo("/topic/activity")
    public Message message(Message message) {
        return messageRepository.save(message);
    }
}
