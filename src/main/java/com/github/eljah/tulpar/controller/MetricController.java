package com.github.eljah.tulpar.controller;

import com.github.eljah.tulpar.model.enums.ProfileDiffType;
import com.github.eljah.tulpar.model.metric.BeforeAndAfterMetric;
import com.github.eljah.tulpar.model.metric.BeforeAndStreamMetric;
import com.github.eljah.tulpar.model.metric.Metric;
import com.github.eljah.tulpar.model.profile.ManuallyChangedProfileDiff;
import com.github.eljah.tulpar.model.profile.ProfileDiff;
import com.github.eljah.tulpar.service.MetricService;
import com.github.eljah.tulpar.service.ProfileDiffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ilya on 03.03.16.
 */
@Controller
public class MetricController {
    @Autowired
    MetricService metricService;

    @RequestMapping("/profiles/metr/aft/add")
    @ResponseStatus(HttpStatus.OK)
    public String addBeforeAfterMetric(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("beforeaction") String beforeaction, @RequestParam("anotheraction") String anotheraction) {
        //User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Metric metric= new BeforeAndAfterMetric();
        metric.setName(name);
        metric.setDescription(description);
        metric.setBeforeAction(beforeaction);
        metric.setAnotherAction(anotheraction);
        metricService.addMetric(metric);
        return "redirect:/profiles/diff/getAll";

    }

    @RequestMapping("/profiles/metr/str/add")
    @ResponseStatus(HttpStatus.OK)
    public String addBeforStreamMetric(@RequestParam("name") String name, @RequestParam("description") String description, @RequestParam("beforeaction") String beforeaction, @RequestParam("anotheraction") String anotheraction) {
        //User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Metric metric= new BeforeAndStreamMetric();
        metric.setName(name);
        metric.setDescription(description);
        metric.setBeforeAction(beforeaction);
        metric.setAnotherAction(anotheraction);
        metricService.addMetric(metric);
        return "redirect:/profiles/diff/getAll";

    }

}
