package com.github.eljah.tulpar.model.metric;

import com.github.eljah.tulpar.model.enums.MetricType;

/**
 * Created by ilya on 29.02.16.
 */
public class BeforeAndAfterMetric extends Metric{

    private MetricType type=MetricType.METRIC_TYPE_BEFORE_AND_AFTER;

    @Override
    public TestRunMetricResult calculate() {
        return null;
    }
}
