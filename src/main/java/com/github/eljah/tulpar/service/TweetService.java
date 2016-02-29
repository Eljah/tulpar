package com.github.eljah.tulpar.service;


import org.springframework.stereotype.Service;
import com.github.eljah.tulpar.model.Tweet;
import com.github.eljah.tulpar.model.User;

import java.util.List;

@Service
public interface TweetService {

    void addTweet(User user, String text);

    List<Tweet> getAll();

    public String doSSH(String command, String host);

}
