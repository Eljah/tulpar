package com.github.eljah.tulpar.model;

import com.github.eljah.tulpar.model.metric.TestRunMetricResult;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by ilya on 29.02.16.
 */

@Entity
public class TestRun {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "test_id")
    Test test;

    @Column
    Date dateStarted;

    @Column
    Date dateEnded;

    @OneToMany
    List<TestRunMetricResult> testRunMetricResults;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public Date getDateEnded() {
        return dateEnded;
    }

    public void setDateEnded(Date dateEnded) {
        this.dateEnded = dateEnded;
    }

    public List<TestRunMetricResult> getTestRunMetricResults() {
        return testRunMetricResults;
    }

    public void setTestRunMetricResults(List<TestRunMetricResult> testRunMetricResults) {
        this.testRunMetricResults = testRunMetricResults;
    }
}
