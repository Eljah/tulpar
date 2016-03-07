package com.github.eljah.tulpar.service;

import com.github.eljah.tulpar.model.Test;
import com.github.eljah.tulpar.model.TestRun;
import com.github.eljah.tulpar.model.metric.Result;
import com.github.eljah.tulpar.model.profile.Profile;
import com.github.eljah.tulpar.model.profile.ProfileDiff;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ilya on 03.03.16.
 */
public interface TestService {
    Test getTest(Long id);

    void addTest(Test test);

    void updateTest(Test test);

    void deleteTest(Test test);

    void cloneTest(Test test);

    void addTestRun(TestRun test);

    void updateTestRun(TestRun test);

    void deleteTestRun(TestRun test);

    void executeTest(Test test);

    void putTestIntoQueue(Test test);

    void startTestQueue();

    void pauseTestQueue();

    void resetTestQueue();

    List<Test> getAll();

    public List<String> printProfileStartAction(Test t);

    public List<String> printProfileEndAction(Test t);

    public List<String> printProfileDiffsLocal(Test t);
    public List<String> printProfileDiffsRemote(Test t);

    public List<String> printMetricsBeforeAction(Test t);

    public List<String> printMetricsForStreamAction(Test t);

    public List<String> printMetricsAfterAction(Test t);

    public List<String> printSelfTest(Test t);

    public void pauseForDuration(Test t);

    public void calculateTestRunResults(TestRun t);

    public List<Result> getTestRunResults(TestRun t);

    public void calculateTestResults(Test t);

    public void setCurrentTestRun(TestRun tr);

    public void removeCurrentTestRun();

    public TestRun getCurrentTestRun();

    public void calculateProfileResults(Profile p);
}
