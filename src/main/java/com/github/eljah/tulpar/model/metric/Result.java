package com.github.eljah.tulpar.model.metric;

import com.github.eljah.tulpar.model.TestRun;

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

    Double average;

    Double averageDelta;

    @Column(nullable = false)
    Double dispersion=0d;


    @Column(nullable = false)
    Double dispersionDelta=0d;

    @OneToOne
    Data max;
    @OneToOne
    Data min;

    @ManyToOne
    TestRun testRun;

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

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
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

    public Double getAverageDelta() {
        return averageDelta;
    }

    public void setAverageDelta(Double averageDelta) {
        this.averageDelta = averageDelta;
    }

    public Double getDispersion() {
        return dispersion;
    }

    public void setDispersion(Double dispersion) {
        this.dispersion = dispersion;
    }

    public TestRun getTestRun() {
        return testRun;
    }

    public void setTestRun(TestRun testRun) {
        this.testRun = testRun;
    }

    public Double getDispersionDelta() {
        return dispersionDelta;
    }

    public void setDispersionDelta(Double dispersionDelta) {
        this.dispersionDelta = dispersionDelta;
    }
}
