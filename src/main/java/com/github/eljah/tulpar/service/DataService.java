package com.github.eljah.tulpar.service;

import com.github.eljah.tulpar.model.TestRun;
import com.github.eljah.tulpar.model.metric.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by eljah32 on 3/5/2016.
 */
public interface DataService {
    void addData(Data data);
    void updateData(Data data);
    List<Data> getDataForTime(Date date);

    List<Data> getDataforTestRun(TestRun testRun);


}
