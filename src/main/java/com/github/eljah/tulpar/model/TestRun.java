package com.github.eljah.tulpar.model;

import com.github.eljah.tulpar.model.metric.Data;
import com.github.eljah.tulpar.model.metric.TestRunMetricResult;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    @OneToMany(fetch = FetchType.EAGER)
    List<TestRunMetricResult> testRunMetricResults;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "testRun")
    Set<Data> datas;

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

    public Set<Data> getDatas() {
        return datas;
    }

    public void setDatas(Set<Data> datas) {
        this.datas = datas;
    }

    //public void addData(Data data)
    //{
    //    this.datas.add(data);
    //}
}
