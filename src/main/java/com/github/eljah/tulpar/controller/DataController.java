package com.github.eljah.tulpar.controller;

import com.github.eljah.tulpar.model.Test;
import com.github.eljah.tulpar.model.TestRun;
import com.github.eljah.tulpar.model.metric.Data;
import com.github.eljah.tulpar.model.profile.Profile;
import com.github.eljah.tulpar.service.DataService;
import com.github.eljah.tulpar.service.MetricService;
import com.github.eljah.tulpar.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by eljah32 on 3/5/2016.
 */

@Controller
public class DataController {
    @Autowired
    DataService dataService;

    @Autowired
    MetricService metricService;

    @Autowired
    TestService testService;


    @RequestMapping("/data/{metric}/{value}")
    @ResponseStatus(HttpStatus.OK)
    public void addTest(@RequestParam("metric") String metric, @RequestParam("value") String value) {
        Data data = new Data();
        data.setMetric(metricService.get(metric));
        data.setValue(Long.parseLong(value));
        data.setTestRun(testService.getCurrentTestRun());
        data.setDate(new Date());
        dataService.addData(data);
    }
}
