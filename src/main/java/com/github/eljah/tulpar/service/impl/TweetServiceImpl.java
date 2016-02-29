package com.github.eljah.tulpar.service.impl;

import com.github.eljah.tulpar.repository.TweetRepository;
import com.github.eljah.tulpar.service.TweetService;
import com.jcraft.jsch.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.eljah.tulpar.annotation.RetryIfException;
import com.github.eljah.tulpar.model.Tweet;
import com.github.eljah.tulpar.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    TweetRepository tweetRepository;

    @Override
    public void addTweet(User user, String text) {
        Tweet tweet = new Tweet();
        tweet.setUser(user);
        tweet.setText(text);
        tweet.setCreatedAt(new Date());
        tweetRepository.save(tweet);
    }

    @RetryIfException(value = 4)
    @Override
    public List<Tweet> getAll() {
        if (Math.random() > 0.5) {
            throw new RuntimeException("HAHAHAHAHAH!");
        }
        return tweetRepository.findAll();
    }

    //@RetryIfException(value = 4)
    public String doSSH(String command, String host)
    {

        JSch jsch=new JSch();
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");


        String lastInputString="";

        try {
            Session session=jsch.getSession("root", host, 22);
            session.setPassword("QmPzGH29");
            session.setConfig(config);
            //todo user info
            session.connect(30000);
            Channel channel=session.openChannel("exec");
            ((ChannelExec)channel).setCommand(command);

            // X Forwarding
            // channel.setXForwarding(true);

            //channel.setInputStream(System.in);
            channel.setInputStream(null);

            //channel.setOutputStream(System.out);

            //FileOutputStream fos=new FileOutputStream("/tmp/stderr");
            //((ChannelExec)channel).setErrStream(fos);
            ((ChannelExec)channel).setErrStream(System.err);

            InputStream in=channel.getInputStream();

            channel.connect();


            byte[] tmp=new byte[1024];
            while(true){
                while(in.available()>0){
                    int i=in.read(tmp, 0, 1024);
                    if(i<0)break;
                    String buff=new String(tmp, 0, i);
                    System.out.print(buff);
                    lastInputString=lastInputString+buff;
                }
                if(channel.isClosed()){
                    if(in.available()>0) continue;
                    System.out.println("exit-status: "+channel.getExitStatus());
                    break;
                }
                try{Thread.sleep(1000);}catch(Exception ee){}
            }
            channel.disconnect();
            session.disconnect();

        } catch (JSchException e) {
            e.printStackTrace();
            throw new RuntimeException("SchException e");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("IOException e");
        }

        return lastInputString;
    }
}
