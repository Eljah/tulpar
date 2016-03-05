package com.github.eljah.tulpar.model.metric;

import com.github.eljah.tulpar.model.TestRun;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ilya on 29.02.16.
 */
@Entity
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    Metric metric;

    @JoinColumn(name = "test_ru_id")
    @ManyToOne
    TestRun testRun;

    Date date;
    Long value;

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

    public TestRun getTestRun() {
        return testRun;
    }

    public void setTestRun(TestRun testRun) {
        this.testRun = testRun;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
