package com.github.eljah.tulpar.service.impl;

import com.github.eljah.tulpar.annotation.LocalShell;
import com.github.eljah.tulpar.annotation.RemoteShell;
import com.github.eljah.tulpar.annotation.TestHttpRequest;
import com.github.eljah.tulpar.model.Test;
import com.github.eljah.tulpar.model.TestRun;
import com.github.eljah.tulpar.model.metric.*;
import com.github.eljah.tulpar.model.profile.Profile;
import com.github.eljah.tulpar.model.profile.ProfileDiff;
import com.github.eljah.tulpar.repository.ProfileRepository;
import com.github.eljah.tulpar.repository.ResultRepository;
import com.github.eljah.tulpar.repository.TestRepository;
import com.github.eljah.tulpar.repository.TestRunRepository;
import com.github.eljah.tulpar.service.TestService;
import com.github.eljah.tulpar.util.TestThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.Queue;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by ilya on 03.03.16.
 */

@Service
public class TestServiceImpl implements TestService {
    static TestRun currentTest = null;

    @Autowired
    TestRepository testRepository;

    @Autowired
    TestRunRepository testRunRepository;

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    ProfileRepository profileRepository;

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
        List<Test> testsOfProfile=test.getProfile().getTests();
        testsOfProfile.add(test);
        test.getProfile().setTests(testsOfProfile);
        testRepository.save(test);
        //profileRepository.save(test.getProfile());
    }

    @Override
    public void updateTest(Test test) {
        testRepository.save(test);
    }

    @Override
    public void cloneTest(Test test) {
        Test cloned = new Test();
        cloned.setProfile(test.getProfile());
        cloned.setDuration(test.getDuration());
        Set<TestRun> testRunList = new HashSet<TestRun>() {
        };
        cloned.setTestRuns(testRunList);
        for (int i = 0; i < test.getTestRuns().size(); i++) {
            TestRun tr = new TestRun();
            //tr.setTest(test);
            testRunList.add(tr);
            addTestRun(tr);
        }
        addTest(cloned);

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

    @LocalShell
    public List<String> printProfileStartAction(Test t) {
        List<String> toReturn = new LinkedList<String>(){};
        if (t.getProfile().getStartAction() != null) {
            toReturn.add(t.getProfile().getStartAction());
            System.out.println("StartAction: " + toReturn);
        }
        return toReturn;
    }

    @LocalShell
    public List<String> printProfileEndAction(Test t) {
        List<String> toReturn = new LinkedList<String>(){};
        if (t.getProfile().getEndAction() != null) {
            toReturn.add(t.getProfile().getEndAction());
            System.out.println("EndAction: " + toReturn);
        }
        return toReturn;
    }

    @LocalShell
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

    @LocalShell
    public List<String> printProfileDiffsLocal(Test t) {
        List<String> toReturn = new LinkedList<String>() {
        };
        if (t.getProfile().getProfileDiffs() != null) {
            for (ProfileDiff pd : t.getProfile().getProfileDiffs()) {
                if (pd.getType().equals("2")) {
                    String toRet = pd.getAction();
                    System.out.println("ProfileDiff: " + toRet);
                    toReturn.add(toRet);
                }
            }
        }
        return toReturn;
    }

    @RemoteShell
    public List<String> printProfileDiffsRemote(Test t) {
        List<String> toReturn = new LinkedList<String>() {
        };
        if (t.getProfile().getProfileDiffs() != null) {
            for (ProfileDiff pd : t.getProfile().getProfileDiffs()) {
                if (pd.getType().equals("3")) {
                String toRet = pd.getAction();
                System.out.println("ProfileDiff: " + toRet);
                toReturn.add(toRet);}
            }
        }
        return toReturn;
    }

    @TestHttpRequest
    //@LocalShell
    public List<String> printSelfTest(Test t) {
        List<String> toReturn = new LinkedList<String>() {
        };
        if (t.getProfile().getMetrics() != null) {
            for (Metric pd : t.getProfile().getMetrics()) {
                String toRet = pd.getName();
                System.out.println("Request will be sent to metrics: " + toRet);
                toReturn.add(toRet);
            }
        }
        return toReturn;
    }


    @Override
    //@TestHttpRequest
    @RemoteShell
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
    //@TestHttpRequest
    //@LocalShell
    @RemoteShell
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

        List<TestRunMetricResult> results = new LinkedList<TestRunMetricResult>();
        List<Metric> metrics = new LinkedList<Metric>();

        //obtaining all collcted Metrics todo both types stram for now only
        for (Data d : t.getDatas()) {
            Metric m = d.getMetric();
            if (!metrics.contains(m)) {
                System.out.println("New metric is fount: " + m);
                metrics.add(m);
                TestRunMetricResult trmr = new TestRunMetricResult();
                trmr.setMetric(m);
                results.add(trmr);
            }
        }

        for (TestRunMetricResult testRunMetricResult : results) {

            int counter = 0;
            long averager = 0;
            long averagerSq = 0;
            long delter = 0;
            long lastValue = 0;
            Data lowestData = null;
            Data highestData = null;
            long highest = Long.MIN_VALUE;
            long lowest = Long.MAX_VALUE;

            for (Data d : t.getDatas()) {
                if (d.getMetric().equals(testRunMetricResult.getMetric())) {
                    averager = averager + d.getValue();
                    averagerSq = averagerSq + d.getValue() * d.getValue();

                    if (counter > 0) {
                        delter = delter + (d.getValue() - lastValue);
                    }
                    counter++;
                    lastValue = d.getValue();
                    if (d.getValue() < lowest) {
                        lowest = d.getValue();
                        lowestData = d;
                    }
                    if (d.getValue() > highest) {
                        highest = d.getValue();
                        highestData = d;
                    }
                }
            }

            if (counter > 0) {
                testRunMetricResult.setAverage(averager / counter);
                testRunMetricResult.setDispersion((averagerSq / counter) - (averager / counter) * (averager / counter));
            } else {
                testRunMetricResult.setAverage(0l);
                testRunMetricResult.setDispersion(0l);
            }
            if (counter > 1) {
                testRunMetricResult.setAverageDelta(delter / (counter - 1));
            } else {
                testRunMetricResult.setAverageDelta(0l);
            }
            testRunMetricResult.setMax(highestData);
            testRunMetricResult.setMin(lowestData);
            resultRepository.save(testRunMetricResult);

            System.out.println("Calculated for metric: " + testRunMetricResult);

        }
        List<TestRunMetricResult> testRunMetricResultList = t.getTestRunMetricResults();
        testRunMetricResultList.addAll(results);
        t.setTestRunMetricResults(testRunMetricResultList);
        updateTestRun(t);
    }

    public List<Result> getTestRunResults(TestRun t
    ) {
        return resultRepository.findByTestRun(t);
    }

    ;


    @Override
    public void calculateTestResults(Test t) {
        System.out.println("Calculating test results");

        List<TestMetricResult> results = new LinkedList<TestMetricResult>();
        List<Metric> metrics = new LinkedList<Metric>();

        //obtaining all collcted Metrics todo both types stram for now only
        for (TestRun tr : t.getTestRuns()) {
            List<TestRunMetricResult> m = tr.getTestRunMetricResults();
            for (TestRunMetricResult trmr : m) {
                if (!metrics.contains(trmr.getMetric())) {
                    System.out.println("New metric is fount: " + trmr.getMetric().getName());
                    metrics.add(trmr.getMetric());
                    TestMetricResult tmr = new TestMetricResult();
                    trmr.setMetric(trmr.getMetric());
                    results.add(tmr);
                }
            }
        }

        results.clear(); //todo >.<

        for (Metric m : metrics) {

            int counter = 0;
            long averager = 0;
            long averagerSq = 0;
            long delter = 0;
            long lastMaxValue = 0;
            long lastMinValue = 0;
            Data lowestData = null;
            Data highestData = null;
            long highest = Long.MIN_VALUE;
            long lowest = Long.MAX_VALUE;

            TestMetricResult tmr = new TestMetricResult();
            tmr.setMetric(m);
            for (TestRun tr : t.getTestRuns()) {
                for (TestRunMetricResult testRunMetricResult : tr.getTestRunMetricResults()) {
                    {
                        if (m.equals(testRunMetricResult.getMetric())) {
                            averagerSq = averagerSq + (testRunMetricResult.getAverage()) * (testRunMetricResult.getAverage());
                            averager = averager + testRunMetricResult.getAverage();
                            delter = delter + testRunMetricResult.getAverageDelta();
                            counter++;
                            lastMaxValue = testRunMetricResult.getMax().getValue();
                            lastMinValue = testRunMetricResult.getMin().getValue();

                            if (lastMinValue < lowest) {
                                lowest = lastMinValue;
                                lowestData = testRunMetricResult.getMin();
                            }
                            if (lastMaxValue > highest) {
                                highest = lastMaxValue;
                                highestData = testRunMetricResult.getMax();
                            }
                        }
                    }
                }
            }
            if (counter > 0) {
                tmr.setAverage(averager / counter);
                tmr.setAverageDelta(delter / (counter));
                tmr.setDispersion((averagerSq / counter) - (averager / counter) * (averager / counter));
            } else {
                tmr.setAverage(0l);
                tmr.setAverageDelta(0l);
                tmr.setDispersion(0l);
            }
            tmr.setMax(highestData);
            tmr.setMin(lowestData);
            resultRepository.save(tmr);
            results.add(tmr);
            System.out.println("Calculated for metric: " + tmr.getMetric());
        }
        List<TestMetricResult> testMetricResultList = t.getTestMetricResults();
        testMetricResultList.addAll(results);
        t.setTestMetricResults(testMetricResultList);
        updateTest(t);
    }


    @Override
    public void calculateProfileResults(Profile p) {
        System.out.println("Calculating profile results");

        List<ProfileMetricResult> results = new LinkedList<ProfileMetricResult>();
        List<Metric> metrics = new LinkedList<Metric>();

        for (Test t: p.getTests()) {
            //obtaining all collcted Metrics todo both types stram for now only
            for (TestRun tr : t.getTestRuns()) {
                List<TestRunMetricResult> m = tr.getTestRunMetricResults();
                for (TestRunMetricResult trmr : m) {
                    if (!metrics.contains(trmr.getMetric())) {
                        System.out.println("New metric is fount: " + trmr.getMetric().getName());
                        metrics.add(trmr.getMetric());
                        ProfileMetricResult tmr = new ProfileMetricResult();
                        trmr.setMetric(trmr.getMetric());
                        results.add(tmr);
                    }
                }
            }

        }
        results.clear(); //todo >.<

        for (Metric m : metrics) {

            int counter = 0;
            long averager = 0;
            long averagerSq = 0;
            long delter = 0;
            long lastMaxValue = 0;
            long lastMinValue = 0;
            Data lowestData = null;
            Data highestData = null;
            long highest = Long.MIN_VALUE;
            long lowest = Long.MAX_VALUE;

            ProfileMetricResult tmr = new ProfileMetricResult();
            tmr.setMetric(m);
            for (Test t: p.getTests()) {

                for (TestRun tr : t.getTestRuns()) {
                    for (TestRunMetricResult testRunMetricResult : tr.getTestRunMetricResults()) {
                        {
                            if (m.equals(testRunMetricResult.getMetric())) {
                                averagerSq = averagerSq + (testRunMetricResult.getAverage()) * (testRunMetricResult.getAverage());
                                averager = averager + testRunMetricResult.getAverage();
                                delter = delter + testRunMetricResult.getAverageDelta();
                                counter++;
                                lastMaxValue = testRunMetricResult.getMax().getValue();
                                lastMinValue = testRunMetricResult.getMin().getValue();

                                if (lastMinValue < lowest) {
                                    lowest = lastMinValue;
                                    lowestData = testRunMetricResult.getMin();
                                }
                                if (lastMaxValue > highest) {
                                    highest = lastMaxValue;
                                    highestData = testRunMetricResult.getMax();
                                }
                            }
                        }
                    }
                }
            }
            if (counter > 0) {
                tmr.setAverage(averager / counter);
                tmr.setAverageDelta(delter / (counter));
                tmr.setDispersion((averagerSq / counter) - (averager / counter) * (averager / counter));
            } else {
                tmr.setAverage(0l);
                tmr.setAverageDelta(0l);
                tmr.setDispersion(0l);
            }
            tmr.setMax(highestData);
            tmr.setMin(lowestData);
            resultRepository.save(tmr);
            results.add(tmr);
            System.out.println("Calculated for metric: " + tmr.getMetric());
        }
        List<ProfileMetricResult> testMetricResultList = p.getProfileMetricResult();
        testMetricResultList.clear();
        testMetricResultList.addAll(results);
        p.setProfileMetricResult(testMetricResultList);
        profileRepository.save(p);
    }


    public void setCurrentTestRun(TestRun tr) {
        System.out.println("Setting to current testRun " + tr.toString());
        currentTest = tr;
    }


    public void removeCurrentTestRun() {
        System.out.println("Removing current testRun " + currentTest.toString());
        for (Data d : currentTest.getDatas()) {
            System.out.println(d.getDate() + " " + d.getValue());
        }
        currentTest = null;
    }

    public TestRun getCurrentTestRun() {
        return currentTest;
    }


}
