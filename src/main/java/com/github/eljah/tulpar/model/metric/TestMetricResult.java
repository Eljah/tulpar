package com.github.eljah.tulpar.model.metric;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ilya on 29.02.16.
 */
@Entity
public class TestMetricResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    Metric metric;

    Long average;
    Long max;
    Long min;
}
