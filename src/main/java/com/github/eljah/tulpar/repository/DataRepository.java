package com.github.eljah.tulpar.repository;

import com.github.eljah.tulpar.model.TestRun;
import com.github.eljah.tulpar.model.metric.Data;
import com.github.eljah.tulpar.model.metric.Metric;
import com.github.eljah.tulpar.model.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by eljah32 on 3/5/2016.
 */

@Repository
public interface DataRepository extends JpaRepository<Data, Long> {
    List<Data> findByMetric(Metric metric);

    List<Data> findByTestRun(TestRun testRun);
    List<Data> findByDate(Date date);
    List<Data> findBydateBetween(Date date1, Date date2);
}
