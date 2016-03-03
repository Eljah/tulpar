package com.github.eljah.tulpar.service;

import com.github.eljah.tulpar.model.Test;
import com.github.eljah.tulpar.model.metric.Data;
import com.github.eljah.tulpar.model.metric.Metric;
import com.github.eljah.tulpar.model.profile.Profile;
import com.github.eljah.tulpar.model.profile.ProfileDiff;

import java.util.List;

/**
 * Created by ilya on 03.03.16.
 */
public interface MetricService {
    void addMetric(Metric metric);

    void updateMetric(Metric metric);

    void deleteMetric(Metric metric);

    String acceptMetricAndData(Metric metric, Data data);

    List<Metric> getAll();

    List<Metric> getForProfile(Profile profile);

    Metric get(String name);

    List<Metric> get(List<String> names);


    int getType();
}
