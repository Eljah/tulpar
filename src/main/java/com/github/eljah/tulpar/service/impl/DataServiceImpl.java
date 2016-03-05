package com.github.eljah.tulpar.service.impl;

import com.github.eljah.tulpar.model.TestRun;
import com.github.eljah.tulpar.model.metric.Data;
import com.github.eljah.tulpar.repository.DataRepository;
import com.github.eljah.tulpar.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by eljah32 on 3/5/2016.
 */
@Service
public class DataServiceImpl implements DataService {
    @Autowired
    DataRepository dataRepository;

    @Override
    public void addData(Data data) {
        dataRepository.save(data);
    }

    @Override
    public void updateData(Data data) {
        dataRepository.save(data);
    }

    @Override
    public List<Data> getDataForTime(Date date) {
        return dataRepository.findByDate(date);
    }

    @Override
    public List<Data> getDataforTestRun(TestRun testRun) {
        return dataRepository.findByTestRun(testRun);
    }
}
