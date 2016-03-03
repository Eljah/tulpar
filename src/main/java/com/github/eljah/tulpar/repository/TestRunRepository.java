package com.github.eljah.tulpar.repository;

import com.github.eljah.tulpar.model.Test;
import com.github.eljah.tulpar.model.TestRun;
import com.github.eljah.tulpar.model.metric.Data;
import com.github.eljah.tulpar.model.metric.TestRunMetricResult;
import com.github.eljah.tulpar.model.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by ilya on 03.03.16.
 */

@Repository
public interface TestRunRepository  extends JpaRepository<TestRun, Long> {

    List<TestRun> findByTest(Test test);

    List<TestRun> findByDateStartedIn(List<Date> name);

    List<TestRun> findByDateEndedIn(List<Date> name);

    TestRun findByTestRunMetricResults(List<TestRunMetricResult> testRunMetricResults);

    TestRun findByDatas(List<Data> testRunMetricResults);
}
