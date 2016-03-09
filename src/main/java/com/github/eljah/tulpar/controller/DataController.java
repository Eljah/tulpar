package com.github.eljah.tulpar.controller;

import com.github.eljah.tulpar.model.Test;
import com.github.eljah.tulpar.model.TestRun;
import com.github.eljah.tulpar.model.metric.Data;
import com.github.eljah.tulpar.model.metric.Metric;
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

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

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


    @RequestMapping("/data/{metric}/{value:.+}")
    @ResponseStatus(HttpStatus.OK)
    public void addTest(@PathVariable("metric") String metric, @PathVariable("value") String value) {
        Data data = new Data();
        data.setDate(new Date());
        Metric m = metricService.get(metric);
        System.out.println(new Date()+": New metric "+metric+ " sent with parameter "+value);
        if (m != null) {
            data.setMetric(m);
            try {
                data.setValue(Double.parseDouble(value));
            }
            catch (NumberFormatException n)
            {
                NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
                Number number = null;
                try {
                    number = format.parse(value);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                data.setValue(number.doubleValue());
            }
            TestRun current = testService.getCurrentTestRun();
            if (current != null) {
                data.setTestRun(current);
                dataService.addData(data);
                Set<Data> datas = current.getDatas();
                datas.add(data);
//            current.getDatas().clear();
                current.setDatas(datas);
                //current.addData(data);
                testService.updateTestRun(current);
            }
        }
    }
}
