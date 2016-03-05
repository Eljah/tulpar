package com.github.eljah.tulpar.service.impl;

import com.github.eljah.tulpar.model.Test;
import com.github.eljah.tulpar.model.metric.Metric;
import com.github.eljah.tulpar.model.profile.Profile;
import com.github.eljah.tulpar.model.profile.ProfileDiff;
import com.github.eljah.tulpar.repository.ProfileDiffRepository;
import com.github.eljah.tulpar.repository.ProfileRepository;
import com.github.eljah.tulpar.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by ilya on 01.03.16.
 */

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    ProfileRepository profileRepository;

    @Override
    public void addProfile(Profile profile) {
        profileRepository.save(profile);
    }

    @Override
    public void addProfile(String profileName) {
        Profile profile = new Profile();
        profile.setName(profileName);
        profileRepository.save(profile);
    }

    @Override
    public void updateProfile(Profile profile) {
        profileRepository.save(profile);

    }

    @Override
    public void cloneProfile(Profile profile) {
        Profile copy=new Profile();
        copy.setName("Copy_of_"+profile.getName());
        copy.setStartAction(profile.getStartAction());
        copy.setEndAction(profile.getEndAction());
        Set<ProfileDiff> lpd=profile.getProfileDiffs();
        Set<ProfileDiff> lpde=new HashSet<>();
        copy.setProfileDiffs(lpde);
        copy.getProfileDiffs().addAll(lpd);
        Set<Metric> lm=profile.getMetrics();
        Set<Metric> lme=new HashSet<>();
        copy.setMetrics(lme);
        copy.getMetrics().addAll(lm);
        profileRepository.save(copy);
    }

    @Override
    public void deleteProfile(Profile profile) {
        profileRepository.delete(profile);
    }


    @Override
    public List<Profile> getAll() {
        return profileRepository.findAll();
    }

    @Override
    public Profile getForTest(Test test) {
        return profileRepository.findByTests(test);
    }

    @Override
    public Profile get(String name) {
        return profileRepository.findByName(name);
    }

    @Override
    public List<Profile> getForProfileDiff(ProfileDiff profileDiff) {
        return profileRepository.findByProfileDiffs(profileDiff);
    }

    @Override
    public void addProfileDiffsToProfile(Profile profile, List<ProfileDiff> profileDiffs) {
         profile.getProfileDiffs().addAll(profileDiffs);
         profileRepository.save(profile);
    }
    
}
