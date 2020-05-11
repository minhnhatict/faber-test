package faber.codetest.nhat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {

    @GetMapping
    public String showIndex() {
        return "index";
    }

    @GetMapping(value = "/book-ticket")
    public String showBookTicket() {
        return "index";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

}
