package com.github.eljah.tulpar.repository;

import com.github.eljah.tulpar.model.Test;
import com.github.eljah.tulpar.model.TestRun;
import com.github.eljah.tulpar.model.metric.Metric;
import com.github.eljah.tulpar.model.metric.TestMetricResult;
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
public interface TestRepository   extends JpaRepository<Test, Long> {

    List<Test> findByProfile(Profile profile);

    List<Test> findByDateStartedIn(List<Date> name);

    List<Test> findByDateEndedIn(List<Date> name);

    Test findByTestRuns(List<TestRun> testRunList);

    Test findByTestMetricResults(List<TestMetricResult> testMetricResults);
}
