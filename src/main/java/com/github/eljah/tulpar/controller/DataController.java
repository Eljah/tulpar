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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
    public void addTest(@PathVariable("metric") String metric, @PathVariable("value") String value) {
        Data data = new Data();
        data.setDate(new Date());
        data.setMetric(metricService.get(metric));
        data.setValue(Long.parseLong(value));
        TestRun current=testService.getCurrentTestRun();
        if (current!=null) {
            data.setTestRun(current);
            dataService.addData(data);
            Set<Data> datas=current.getDatas();
            datas.add(data);
//            current.getDatas().clear();
            current.setDatas(datas);
            //current.addData(data);
            testService.updateTestRun(current);
        }
    }
}
