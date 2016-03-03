package com.github.eljah.tulpar.util;

import com.github.eljah.tulpar.model.Test;
import com.github.eljah.tulpar.model.TestRun;
import com.github.eljah.tulpar.service.TestService;
import com.github.eljah.tulpar.service.impl.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;

/**
 * Created by eljah32 on 3/3/2016.
 */
@Component
public class TestThread extends Thread {
    @Autowired
    TestService testService;


    static ArrayDeque q;

    static boolean workFlag = false;

    public TestService getTestService() {
        return testService;
    }

    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    public static ArrayDeque getQ() {
        return q;
    }

    public static void setQ(ArrayDeque q) {
        TestThread.q = q;
    }

    public static boolean isWorkFlag() {
        return workFlag;
    }

    public static void setWorkFlag(boolean workFlag) {
        TestThread.workFlag = workFlag;
    }

    public TestThread(ArrayDeque q) {
        TestThread.q = q;
    }

    public TestThread() {
    }


    @Override
    public void run() {
        while (true) {
            if (!q.isEmpty()) {
                Test t = (Test) q.pop();
                if (t != null) {
                    testService.printProfileDiffs(t);
                    testService.printMetricsForStreamAction(t);
                    //todo metrics setup for stream metrics

                    for (TestRun tr : t.getTestRuns()) {
                        testService.printMetricsBeforeAction(t);
                        //todo metrics before action
                        testService.printProfileStartAction(t);
                        testService.pauseForDuration(t);
                        //todo test runs loop; do test run metrics calculation
                        //todo duration pause during each loop
                        //to metrics last action if it is
                        //todo calculate test metrics
                        //todo
                        testService.printProfileEndAction(t);
                        testService.printMetricsAfterAction(t);
                        testService.calculateTestRunResults(tr);
                    }
                    testService.calculateTestResults(t);
                    t.setExecuted(true);
                    testService.updateTest(t);
                } else {
                    throw new RuntimeException("Surprisingly NULL Test came into queue");
                }
            } else {
                try {
                    this.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void stopQueue() {
        workFlag = false;
    }


    public void startQueue() {
        workFlag = true;
    }
}
