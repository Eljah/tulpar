package com.github.eljah.tulpar.service;

import com.github.eljah.tulpar.model.Test;
import com.github.eljah.tulpar.model.profile.Profile;

/**
 * Created by ilya on 03.03.16.
 */
public interface TestService {
    void addTest(Test test);

    void updateTest(Test test);

    void deleteTest(Test test);

    void executeTest(Test test);

    void putTestIntoQueue(Test test);

}
