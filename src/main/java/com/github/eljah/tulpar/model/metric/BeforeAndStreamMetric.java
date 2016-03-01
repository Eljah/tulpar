package com.github.eljah.tulpar.model.metric;

import com.github.eljah.tulpar.model.enums.MetricType;

/**
 * Created by ilya on 29.02.16.
 */
public class BeforeAndStreamMetric extends Metric {

    private MetricType type=MetricType.METRIC_TYPE_BEFORE_AND_STREAM;

    @Override
    public TestRunMetricResult calculate() {
        return null;
    }
}
