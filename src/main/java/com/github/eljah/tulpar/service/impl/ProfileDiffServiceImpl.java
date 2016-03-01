package com.github.eljah.tulpar.service.impl;

import com.github.eljah.tulpar.model.enums.ProfileDiffType;
import com.github.eljah.tulpar.model.profile.Profile;
import com.github.eljah.tulpar.model.profile.ProfileDiff;
import com.github.eljah.tulpar.repository.ProfileDiffRepository;
import com.github.eljah.tulpar.service.ProfileDiffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ilya on 01.03.16.
 */

@Service("gen")
public class ProfileDiffServiceImpl implements ProfileDiffService {

    @Autowired
    ProfileDiffRepository profileDiffRepository;

    @Override
    public void addProfileDiff(ProfileDiff profileDiff) {
    profileDiffRepository.save(profileDiff);
    }

    @Override
    public void updateProfileDiff(ProfileDiff profileDiff) {
        profileDiffRepository.save(profileDiff);
    }

    @Override
    public void deleteProfileDiff(ProfileDiff profileDiff) {
        profileDiffRepository.delete(profileDiff);
    }

    @Override
    public String doAction(ProfileDiff profileDiff) {
        return profileDiff.getAction();
    }

    @Override
    public List<ProfileDiff> getAll() {
        return  profileDiffRepository.findAll();
    }

    @Override
    public List<ProfileDiff> getForProfile(Profile profile) {
        return profileDiffRepository.findByProfiles(profile);
    }

    @Override
    public List<ProfileDiff> get(List<String> names) {
        return profileDiffRepository.findByNameIn(names);
    }

    @Override
    public int getType() {
        return ProfileDiffType.PROFILE_DIFF_GENERIC_PROGRAMMATICAL.getValue();
    }



}
