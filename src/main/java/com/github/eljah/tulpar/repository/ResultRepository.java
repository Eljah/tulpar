package com.github.eljah.tulpar.repository;

import com.github.eljah.tulpar.model.Test;
import com.github.eljah.tulpar.model.TestRun;
import com.github.eljah.tulpar.model.metric.Metric;
import com.github.eljah.tulpar.model.metric.Result;
import com.github.eljah.tulpar.model.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by eljah32 on 3/6/2016.
 */

@Repository
public interface ResultRepository  extends JpaRepository<Result, Long> {
    List<Result> findByTestRun(TestRun testRun);
}
