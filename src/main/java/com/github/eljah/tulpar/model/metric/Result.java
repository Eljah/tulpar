package com.github.eljah.tulpar.model.metric;

import javax.persistence.*;

/**
 * Created by eljah32 on 3/6/2016.
 */

@Entity
@Inheritance
@DiscriminatorColumn(name="type")
public abstract class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    Metric metric;

    Long average;

    @OneToOne
    Data max;
    @OneToOne
    Data min;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }

    public Long getAverage() {
        return average;
    }

    public void setAverage(Long average) {
        this.average = average;
    }

    public Data getMax() {
        return max;
    }

    public void setMax(Data max) {
        this.max = max;
    }

    public Data getMin() {
        return min;
    }

    public void setMin(Data min) {
        this.min = min;
    }
}