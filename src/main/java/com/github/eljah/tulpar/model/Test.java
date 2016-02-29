package com.github.eljah.tulpar.model;

import com.github.eljah.tulpar.model.profile.Profile;

import javax.persistence.*;

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
}
