package com.github.eljah.tulpar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.github.eljah.tulpar.model.Tweet;
import com.github.eljah.tulpar.model.User;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

    List<Tweet> findByUser(User user);

}
