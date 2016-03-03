package com.github.eljah.tulpar.service;

import com.github.eljah.tulpar.model.Test;
import com.github.eljah.tulpar.model.TestRun;
import com.github.eljah.tulpar.model.profile.Profile;
import com.github.eljah.tulpar.model.profile.ProfileDiff;

import java.util.List;

/**
 * Created by ilya on 03.03.16.
 */
public interface TestService {
    void addTest(Test test);

    void updateTest(Test test);

    void deleteTest(Test test);

    void addTestRun(TestRun test);

    void updateTestRun(TestRun test);

    void deleteTestRun(TestRun test);

    void executeTest(Test test);

    void putTestIntoQueue(Test test);



    List<Test> getAll();

}
