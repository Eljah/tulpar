package com.github.eljah.tulpar.repository;

import com.github.eljah.tulpar.model.Test;
import com.github.eljah.tulpar.model.metric.Metric;
import com.github.eljah.tulpar.model.profile.Profile;
import com.github.eljah.tulpar.model.profile.ProfileDiff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ilya on 03.03.16.
 */

@Repository
public interface MetricRepository  extends JpaRepository<Metric, Long> {

    List<Metric> findByProfiles(Profile profile);

    List<Metric> findByNameIn(List<String> name);

    Metric findByName(String name);

}
