package com.github.eljah.tulpar.service.impl;

import com.github.eljah.tulpar.model.Test;
import com.github.eljah.tulpar.model.TestRun;
import com.github.eljah.tulpar.model.profile.ProfileDiff;
import com.github.eljah.tulpar.repository.TestRepository;
import com.github.eljah.tulpar.repository.TestRunRepository;
import com.github.eljah.tulpar.service.TestService;
import com.github.eljah.tulpar.util.TestThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.Queue;

import javax.annotation.PostConstruct;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ilya on 03.03.16.
 */

@Service
public class TestServiceImpl implements TestService {


    @Autowired
    TestRepository testRepository;

    @Autowired
    TestRunRepository testRunRepository;

    static ArrayDeque<Test> testQueue = new ArrayDeque<Test>();

    @Autowired
    TestThread tt;


    @PostConstruct
    public void initQueue() {
        tt.setQ(testQueue);
        tt.start();
    }

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
    public void addTestRun(TestRun test) {
        testRunRepository.save(test);
    }

    @Override
    public void updateTestRun(TestRun test) {
        testRunRepository.save(test);
    }

    @Override
    public void deleteTestRun(TestRun test) {
        testRunRepository.delete(test);
    }


    @Override
    public void executeTest(Test test) {

    }

    @Override
    public void putTestIntoQueue(Test test) {
        testQueue.add(test);
    }

    @Override
    public void startTestQueue() {

    }

    @Override
    public void pauseTestQueue() {

    }

    @Override
    public void resetTestQueue() {

    }


    @Override
    public List<Test> getAll() {
        return testRepository.findAll();
    }


    public String printProfileStartAction(Test t) {
        String toReturn = null;
        if (t.getProfile().getStartAction() != null) {
            toReturn = t.getProfile().getStartAction();
            System.out.println(toReturn);
        }
        return toReturn;
    }

    public String printProfileEndAction(Test t) {
        String toReturn = null;
        if (t.getProfile().getEndAction() != null) {
            toReturn = t.getProfile().getEndAction();
            System.out.println(toReturn);
        }
        return toReturn;
    }

    public List<String> printProfileDiffs(Test t) {
        List<String> toReturn = new LinkedList<String>() {
        };
        if (t.getProfile().getProfileDiffs() != null) {
            for (ProfileDiff pd : t.getProfile().getProfileDiffs()) {
                String toRet = pd.getAction();
                System.out.println(toRet);
                toReturn.add(toRet);
            }
        }
        return toReturn;
    }

    public Test getTest(Long id) {
        return testRepository.findOne(id);
    }
}
