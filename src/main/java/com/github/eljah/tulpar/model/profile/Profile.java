package com.github.eljah.tulpar.model.profile;

import com.github.eljah.tulpar.model.Test;
import com.github.eljah.tulpar.model.metric.Metric;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by ilya on 29.02.16.
 */
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_diff_id")
    private Set<ProfileDiff> profileDiffs;

    @JoinColumn(name = "metric_id")
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Metric> metrics;

    @OneToMany
    private List<Test> tests;

    String name;

    @Column
    String startAction;

    @Column
    String endAction;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<ProfileDiff> getProfileDiffs() {
        return profileDiffs;
    }

    public void setProfileDiffs(Set<ProfileDiff> profileDiffs) {
        this.profileDiffs = profileDiffs;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Metric> getMetrics() {
        return metrics;
    }

    public void setMetrics(Set<Metric> metrics) {
        this.metrics = metrics;
    }

    public String getStartAction() {
        return startAction;
    }

    public void setStartAction(String startAction) {
        this.startAction = startAction;
    }

    public String getEndAction() {
        return endAction;
    }

    public void setEndAction(String endAction) {
        this.endAction = endAction;
    }

}
