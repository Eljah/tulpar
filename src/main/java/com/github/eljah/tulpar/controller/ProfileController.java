package com.github.eljah.tulpar.controller;

import com.github.eljah.tulpar.model.Tweet;
import com.github.eljah.tulpar.model.User;
import com.github.eljah.tulpar.model.enums.ProfileDiffType;
import com.github.eljah.tulpar.model.metric.Metric;
import com.github.eljah.tulpar.model.profile.Profile;
import com.github.eljah.tulpar.model.profile.ProfileDiff;
import com.github.eljah.tulpar.service.MetricService;
import com.github.eljah.tulpar.service.ProfileDiffService;
import com.github.eljah.tulpar.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Created by ilya on 01.03.16.
 */

@Controller
public class ProfileController {
    @Autowired
    ProfileService profileService;

    @Autowired
    MetricService metricService;

    @Autowired
    List<ProfileDiffService> profileDiffServices;

    @RequestMapping("/profiles/add")
    @ResponseStatus(HttpStatus.OK)
    public String addProfile(@RequestParam("name") String name) {
        //User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        profileService.addProfile(name);
        return "redirect:/profiles/diff/getAll";
    }

    @RequestMapping("/profiles/getAll")
    public String getAllProfilesPage(Model model) {
        List<Profile> profiles = profileService.getAll();
        List<ProfileDiff> profileDiffs = null;
        for (ProfileDiffService pf: profileDiffServices)
        {
            profileDiffs=pf.getAll();
            if (profileDiffs!=null)
            {
                break;
            }
        }
        List<Metric> metrics = metricService.getAll();

        model.addAttribute("profiles", profiles);
        model.addAttribute("profilediffsall", profileDiffs);
        model.addAttribute("metrics", metrics);

        return "profiles-list";
    }

    @RequestMapping("/profiles/{profilename}/addprofile")
    @ResponseStatus(HttpStatus.OK)
    public String addProfileDiffToProfile(@PathVariable("profilename") String profilename , @RequestParam("diffs") List<String> diffs,Model model) {
        Profile profile = profileService.get(profilename);
        List<ProfileDiff> profileDiffs = null;
        for (ProfileDiffService pf: profileDiffServices)
        {
            profileDiffs=pf.get(diffs);
            if (profileDiffs!=null)
            {
                break;
            }
        }
        profile.getProfileDiffs().clear();
        profile.getProfileDiffs().addAll(profileDiffs);
        profileService.updateProfile(profile);

        List<Profile> profiles = profileService.getAll();
        model.addAttribute("profiles", profiles);

        List<ProfileDiff> profileDiffsAll = null;
        for (ProfileDiffService pf: profileDiffServices)
        {
            profileDiffsAll=pf.getAll();
            if (profileDiffsAll!=null)
            {
                break;
            }
        }

        model.addAttribute("profilediffsall", profileDiffsAll);

        return "redirect:/profiles/getAll";
    }

    @RequestMapping("/profiles/{profilename}/addmetric")
    @ResponseStatus(HttpStatus.OK)
    public String addMetricToProfile(@PathVariable("profilename") String profilename , @RequestParam("diffs") List<String> diffs,Model model) {
        Profile profile = profileService.get(profilename);

        List<Metric> metrics = metricService.get(diffs);
        profile.getMetrics().clear();
        profile.getMetrics().addAll(metrics);
        profileService.updateProfile(profile);

        //for (Metric m: metrics)
        //{
        //    metricService.updateMetric(m);
        //}

        List<Profile> profiles = profileService.getAll();
        model.addAttribute("profiles", profiles);
        model.addAttribute("metrics", metrics);


        return "redirect:/profiles/getAll";
    }


}
