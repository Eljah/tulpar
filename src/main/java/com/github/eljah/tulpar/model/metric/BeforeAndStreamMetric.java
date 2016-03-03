package com.github.eljah.tulpar.model.metric;

import com.github.eljah.tulpar.model.enums.MetricType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by ilya on 29.02.16.
 */

@Entity
@DiscriminatorValue("1")
public class BeforeAndStreamMetric extends Metric {

    @Override
    public TestRunMetricResult calculate() {
        return null;
    }

    public String getType() {
        return "1";
    }
}
