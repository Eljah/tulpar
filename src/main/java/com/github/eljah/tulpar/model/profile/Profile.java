package com.github.eljah.tulpar.model.profile;

import com.github.eljah.tulpar.model.Test;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ilya on 29.02.16.
 */
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany
    private List<ProfileDiff> profileDiffs;

    @OneToMany
    private List<Test> tests;

    String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ProfileDiff> getProfileDiffs() {
        return profileDiffs;
    }

    public void setProfileDiffs(List<ProfileDiff> profileDiffs) {
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
}
