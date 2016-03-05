package com.github.eljah.tulpar.service.impl;

import com.github.eljah.tulpar.model.Test;
import com.github.eljah.tulpar.model.TestRun;
import com.github.eljah.tulpar.model.metric.Metric;
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
    static TestRun currentTest=null;

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

    public Test getTest(Long id) {
        return testRepository.findOne(id);
    }

    public String printProfileStartAction(Test t) {
        String toReturn = null;
        if (t.getProfile().getStartAction() != null) {
            toReturn = t.getProfile().getStartAction();
            System.out.println("StartAction: " + toReturn);
        }
        return toReturn;
    }

    public String printProfileEndAction(Test t) {
        String toReturn = null;
        if (t.getProfile().getEndAction() != null) {
            toReturn = t.getProfile().getEndAction();
            System.out.println("EndAction: " + toReturn);
        }
        return toReturn;
    }

    public List<String> printProfileDiffs(Test t) {
        List<String> toReturn = new LinkedList<String>() {
        };
        if (t.getProfile().getProfileDiffs() != null) {
            for (ProfileDiff pd : t.getProfile().getProfileDiffs()) {
                String toRet = pd.getAction();
                System.out.println("ProfileDiff: " + toRet);
                toReturn.add(toRet);
            }
        }
        return toReturn;
    }

    @Override
    public List<String> printMetricsBeforeAction(Test t) {
        List<String> toReturn = new LinkedList<String>() {
        };
        if (t.getProfile().getMetrics() != null) {
            for (Metric pd : t.getProfile().getMetrics()) {
                String toRet = pd.getBeforeAction();
                System.out.println("MetricsBefore: " + toRet);
                toReturn.add(toRet);
            }
        }
        return toReturn;
    }

    @Override
    public List<String> printMetricsForStreamAction(Test t) {
        List<String> toReturn = new LinkedList<String>() {
        };
        if (t.getProfile().getMetrics() != null) {
            for (Metric pd : t.getProfile().getMetrics()) {
                if (pd.getType().equals("0")) {
                    String toRet = pd.getAnotherAction();
                    System.out.println("MetricsStream: " + toRet);
                    toReturn.add(toRet);
                }
            }
        }
        return toReturn;

    }

    @Override
    public List<String> printMetricsAfterAction(Test t) {
        List<String> toReturn = new LinkedList<String>() {
        };
        if (t.getProfile().getMetrics() != null) {
            for (Metric pd : t.getProfile().getMetrics()) {
                if (pd.getType().equals("1")) {
                    String toRet = pd.getAnotherAction();
                    System.out.println("MetricsAfter: " + toRet);
                    toReturn.add(toRet);
                }
            }
        }
        return toReturn;
    }

    @Override
    public void pauseForDuration(Test t) {
        long DurationInMins = t.getDuration();
        try {
            Thread.sleep((DurationInMins * 1000 * 60));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void calculateTestRunResults(TestRun t) {
        System.out.println("Calculating test run results");
    }

    @Override
    public void calculateTestResults(Test t) {
        System.out.println("Calculating test results");
    }


    public void setCurrentTestRun(TestRun tr)
    {
        currentTest=tr;
    }

    public TestRun getCurrentTestRun()
    {
        return currentTest;
    }


}
