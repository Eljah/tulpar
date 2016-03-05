package com.github.eljah.tulpar.controller;

import com.github.eljah.tulpar.model.Test;
import com.github.eljah.tulpar.model.TestRun;
import com.github.eljah.tulpar.model.profile.Profile;
import com.github.eljah.tulpar.service.ProfileService;
import com.github.eljah.tulpar.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ilya on 03.03.16.
 */

@Controller
public class TestController {

    @Autowired
    TestService testService;

    @Autowired
    ProfileService profileService;


    @RequestMapping("/tests/add")
    @ResponseStatus(HttpStatus.OK)
    public String addTest(@RequestParam("profilename") String profilename, @RequestParam("runduration") String runduration, @RequestParam("numberofruns") String numberofruns) {
        //User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Profile profile = profileService.get(profilename);
        Test test= new Test();
        test.setProfile(profile);
        test.setDuration(Long.parseLong(runduration));
        Integer numberOfRuns=Integer.parseInt(numberofruns);
        List<TestRun> testRunList=new LinkedList<TestRun>(){};
        test.setTestRuns(testRunList);
        //testService.addTest(test);
        for (int i=0; i<numberOfRuns; i++)
        {
            TestRun tr=new TestRun();
            //tr.setTest(test);
            testRunList.add(tr);
            testService.addTestRun(tr);
        }

        //test.setTestRuns(testRunList);
        testService.updateTest(test);

        test=testService.getTest(test.getId());



        return "redirect:/tests";
    }

    @RequestMapping("/tests")
    @ResponseStatus(HttpStatus.OK)
    public String addTest(Model model) {
        //User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<Test> tests=testService.getAll();
        List<Profile> profiles=profileService.getAll();

        model.addAttribute("profiles", profiles);
        model.addAttribute("tests", tests);

        return "tests-list";
    }

    @RequestMapping("/tests/{testid}/pull")
    @ResponseStatus(HttpStatus.OK)
    public String addPullTestToQueue(@PathVariable("testid") String testid, Model model) {
        //User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Test test=testService.getTest(Long.parseLong(testid));
        test.setPlanned(true);
        testService.putTestIntoQueue(test);
        testService.updateTest(test);
        return "redirect:/tests";
    }

}
