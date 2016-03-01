package com.github.eljah.tulpar.service;

import com.github.eljah.tulpar.model.Test;
import com.github.eljah.tulpar.model.profile.Profile;
import com.github.eljah.tulpar.model.profile.ProfileDiff;

import java.util.List;

/**
 * Created by ilya on 01.03.16.
 */
public interface ProfileService {
    void addProfile(Profile profile);

    void addProfile(String profileName);

    void updateProfile(Profile profile);

    void deleteProfile(Profile profile);

    void addProfileDiffsToProfile(Profile profile, List<ProfileDiff> profileDiffs);

    List<Profile> getAll();

    Profile getForTest(Test test);

    Profile get(String name);

    List<Profile> getForProfileDiff(ProfileDiff profileDiff);
}
