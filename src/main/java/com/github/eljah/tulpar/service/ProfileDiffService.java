package com.github.eljah.tulpar.service;

import com.github.eljah.tulpar.model.profile.Profile;
import com.github.eljah.tulpar.model.profile.ProfileDiff;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ilya on 01.03.16.
 */

public interface ProfileDiffService {
    void addProfileDiff(ProfileDiff profileDiff);

    void updateProfileDiff(ProfileDiff profileDiff);

    void deleteProfileDiff(ProfileDiff profileDiff);

    String doAction(ProfileDiff profileDiff);

    List<ProfileDiff> getAll();

    List<ProfileDiff> getForProfile(Profile profile);

    List<ProfileDiff> get(List<String> names);

    int getType();
}
