package com.github.eljah.tulpar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.github.eljah.tulpar.model.Tweet;
import com.github.eljah.tulpar.model.User;
import com.github.eljah.tulpar.repository.UserRepository;
import com.github.eljah.tulpar.service.TweetService;

import java.util.List;

@Controller
public class TweetController {

    @Autowired //FIXME BAD!! Almost
    UserRepository userRepository;

    @Autowired
    TweetService tweetService;

    @RequestMapping("/tweets/add")
    @ResponseStatus(HttpStatus.OK)
    public void addTweet(@RequestParam("text") String text) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        tweetService.addTweet(user, text);
        String result=tweetService.doSSH("date","192.168.0.2");
        tweetService.addTweet(user,result);
    }

    @RequestMapping("/tweets/getAll")
    public String getAllTweetsPage(Model model) {
        List<Tweet> tweets = tweetService.getAll();
        model.addAttribute("tweets", tweets);
        return "tweet-list";
    }

}
