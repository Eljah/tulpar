package com.github.eljah.tulpar.model;

import com.github.eljah.tulpar.model.metric.TestMetricResult;
import com.github.eljah.tulpar.model.metric.TestRunMetricResult;
import com.github.eljah.tulpar.model.profile.Profile;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by ilya on 29.02.16.
 */

@Entity
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    Profile profile;

    @Column
    long duration;

    @Column
    Date dateStarted;

    @Column
    Date dateEnded;

    @OneToMany
    List<TestMetricResult> testMetricResults;

    @OneToMany(fetch = FetchType.EAGER)
    Set<TestRun> testRuns;

    @Column
    Boolean executed=false;

    @Column
    Boolean planned=false;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Date getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Date dateStarted) {
        this.dateStarted = dateStarted;
    }

    public Set<TestRun> getTestRuns() {
        return testRuns;
    }

    public void setTestRuns(Set<TestRun> testRuns) {
        this.testRuns = testRuns;
    }

    public Date getDateEnded() {
        return dateEnded;
    }

    public void setDateEnded(Date dateEnded) {
        this.dateEnded = dateEnded;
    }

    public List<TestMetricResult> getTestMetricResults() {
        return testMetricResults;
    }

    public void setTestMetricResults(List<TestMetricResult> testMetricResults) {
        this.testMetricResults = testMetricResults;
    }

    public Boolean getExecuted() {
        return executed;
    }

    public void setExecuted(Boolean executed) {
        this.executed = executed;
    }

    public Boolean getPlanned() {
        return planned;
    }

    public void setPlanned(Boolean planned) {
        this.planned = planned;
    }
}
