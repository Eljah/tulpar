package com.github.eljah.tulpar.model.metric;

import com.github.eljah.tulpar.model.Test;
import com.github.eljah.tulpar.model.enums.MetricType;
import com.github.eljah.tulpar.model.enums.ProfileDiffType;
import com.github.eljah.tulpar.model.profile.Profile;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by ilya on 29.02.16.
 */
@Entity
@Inheritance
@DiscriminatorColumn(name="type")
public abstract class Metric {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    String name;

    String description;

    String beforeAction;

    String anotherAction;

    @ManyToMany(mappedBy = "metrics")
    Set<Profile> profiles;

    public void setName(String name)
    {
        this.name=name;
    }

    public String getName()
    {
        return this.name;
    }


    public void setBeforeAction(String beforeAction)
    {
        this.beforeAction=beforeAction;
    }

    public String getBeforeAction()
    {
        return this.beforeAction;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAnotherAction() {
        return anotherAction;
    }

    public void setAnotherAction(String anotherAction) {
        this.anotherAction = anotherAction;
    }

    public Set<Profile> getProfiles() {
        return profiles;
    }

    public void setProfiles(Set<Profile> profiles) {
        this.profiles = profiles;
    }

    public abstract String getType();


    public abstract TestRunMetricResult calculate();

    @Override
    public String toString()
    {
        return (this.name);
    }



}
