package com.github.eljah.tulpar.service.impl;

import com.github.eljah.tulpar.model.Test;
import com.github.eljah.tulpar.repository.TestRepository;
import com.github.eljah.tulpar.repository.TestRunRepository;
import com.github.eljah.tulpar.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.Queue;

import java.util.ArrayDeque;

/**
 * Created by ilya on 03.03.16.
 */

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestRepository testRepository;

    @Autowired
    TestRunRepository testRunRepository;

    static ArrayDeque<Test> testQueue=new ArrayDeque<Test>();


    @Override
    public void addTest(Test test) {
testRepository.save(test);
    }

    @Override
    public void updateTest(Test test) {
        testRepository.save(test);
    }

    @Override
    public void deleteTest(Test test) {
        testRepository.delete(test);
    }

    @Override
    public void executeTest(Test test) {

    }

    @Override
    public void putTestIntoQueue(Test test) {

    }
}
