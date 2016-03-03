package com.github.eljah.tulpar.service.impl;

import com.github.eljah.tulpar.model.Test;
import com.github.eljah.tulpar.model.metric.Data;
import com.github.eljah.tulpar.model.metric.Metric;
import com.github.eljah.tulpar.model.profile.Profile;
import com.github.eljah.tulpar.model.profile.ProfileDiff;
import com.github.eljah.tulpar.repository.MetricRepository;
import com.github.eljah.tulpar.service.MetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ilya on 03.03.16.
 */

@Service
public class MetricServiceImpl implements MetricService {
@Autowired
    MetricRepository metricRepository;


    @Override
    public void addMetric(Metric metric) {
        metricRepository.save(metric);
    }

    @Override
    public void updateMetric(Metric metric) {
metricRepository.save(metric);
    }

    @Override
    public void deleteMetric(Metric metric) {
metricRepository.delete(metric);
    }

    @Override
    public String acceptMetricAndData(Metric metric, Data data) {
        return null;
    }

    @Override
    public List<Metric> getAll() {
        return metricRepository.findAll();
    }

    @Override
    public List<Metric> getForProfile(Profile profile) {
        return metricRepository.findByProfiles(profile);
    }


    @Override
    public Metric get(String name) {
        return metricRepository.findByName(name);
    }

    @Override
    public List<Metric> get(List<String> names) {
        return metricRepository.findByNameIn(names);
    }

    @Override
    public int getType() {
        return 0;
    }
}
