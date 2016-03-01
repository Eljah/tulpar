package com.github.eljah.tulpar.repository;

import com.github.eljah.tulpar.model.Test;
import com.github.eljah.tulpar.model.TestRun;
import com.github.eljah.tulpar.model.Tweet;
import com.github.eljah.tulpar.model.User;
import com.github.eljah.tulpar.model.profile.Profile;
import com.github.eljah.tulpar.model.profile.ProfileDiff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ilya on 01.03.16.
 */

@Repository
public interface ProfileRepository  extends JpaRepository<Profile, Long> {

    List<Profile> findByProfileDiffs(ProfileDiff profileDiff);

    Profile findByName(String name);

    Profile findByTests(Test test);

    //List<Profile> findByTestRun(TestRun testRun);

}
