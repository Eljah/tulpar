package com.github.eljah.tulpar.controller;

import com.github.eljah.tulpar.model.enums.ProfileDiffType;
import com.github.eljah.tulpar.model.profile.*;
import com.github.eljah.tulpar.service.ProfileDiffService;
import com.github.eljah.tulpar.service.impl.LocalChangedProfileDiffServiceImpl;
import com.github.eljah.tulpar.service.impl.ManuallyChangedProfileDiffServiceImpl;
import com.github.eljah.tulpar.service.impl.ProfileDiffServiceImpl;
import com.github.eljah.tulpar.service.impl.RemoteChangedProfileDiffServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Created by ilya on 01.03.16.
 */
@Controller
public class ProfileDiffController {
    @Autowired
    List<ProfileDiffService> profileDiffServices;

    @RequestMapping("/profiles/diff/add")
    @ResponseStatus(HttpStatus.OK)
    public String addProfileDiff() {

        return "diffadd";
    }



    @RequestMapping("/profiles/diff/man/add")
    @ResponseStatus(HttpStatus.OK)
    public String addProfileDiffMan(@RequestParam("name") String name, @RequestParam("description") String description,@RequestParam("action") String action) {
        //User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ProfileDiff profileDiff= new ManuallyChangedProfileDiff();
        profileDiff.setName(name);
        profileDiff.setDescription(description);
        profileDiff.setAction(action);

        for (ProfileDiffService pf: profileDiffServices)
        {
            if (pf.getType()== ProfileDiffType.PROFILE_DIFF_MANUAL.getValue())
            {
                pf.addProfileDiff(profileDiff);
            }
        }
        return "redirect:/profiles/diff/getAll";

    }

    @RequestMapping("/profiles/diff/loc/add")
    @ResponseStatus(HttpStatus.OK)
    public String addProfileDiffLoc(@RequestParam("name") String name, @RequestParam("description") String description,@RequestParam("action") String action) {
        //User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ProfileDiff profileDiff= new LocalChangedProfileDiff();
        profileDiff.setName(name);
        profileDiff.setDescription(description);
        profileDiff.setAction(action);
        for (ProfileDiffService pf: profileDiffServices)
        {
            if (pf.getType()== ProfileDiffType.PROFILE_DIFF_LOCAL_COMMAND.getValue())
            {
                pf.addProfileDiff(profileDiff);
            }
        }
        return "redirect:/profiles/diff/getAll";
    }

    @RequestMapping("/profiles/diff/rem/add")
    @ResponseStatus(HttpStatus.OK)
    public String addProfileDiffRem(@RequestParam("name") String name, @RequestParam("description") String description,@RequestParam("action") String action) {
        //User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ProfileDiff profileDiff= new RemoteChangedProfileDiff();
        profileDiff.setName(name);
        profileDiff.setDescription(description);
        profileDiff.setAction(action);

        for (ProfileDiffService pf: profileDiffServices)
        {
            if (pf.getType()== ProfileDiffType.PROFILE_DIFF_LOCAL_COMMAND.getValue())
            {
                pf.addProfileDiff(profileDiff);
            }
        }
        return "redirect:/profiles/diff/getAll";
    }

    @RequestMapping("/profiles/diff/getAll")
    public String getAllProfileDiffPage(Model model) {
        List<ProfileDiff> profilediffs=null;
        for (ProfileDiffService pf: profileDiffServices)
        {
            System.out.println(pf.getClass().getName());
            profilediffs=pf.getAll();
            if (profilediffs!=null)
            {
                break;
            }
        }
        model.addAttribute("profilediffs", profilediffs);
        return "profilediffs-list";
    }

}
