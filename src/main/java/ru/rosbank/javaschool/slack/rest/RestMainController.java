package ru.rosbank.javaschool.slack.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rosbank.javaschool.slack.domain.User;
import ru.rosbank.javaschool.slack.repository.MessageRepository;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class RestMainController {
    private final MessageRepository messageRepository;

    @Value("${spring.profiles.active}")
    private String profile;

    @Autowired
    public RestMainController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user) {
        HashMap<Object, Object> data = new HashMap<>();

        data.put("profile", user);
        data.put("messages", messageRepository.findAll());

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));


        return "index";
    }
}