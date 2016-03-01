package com.github.eljah.tulpar.model.metric;

import com.github.eljah.tulpar.model.Test;
import com.github.eljah.tulpar.model.enums.MetricType;
import com.github.eljah.tulpar.model.enums.ProfileDiffType;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ilya on 29.02.16.
 */
@Entity
public abstract class Metric {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    String name;

    String beforeAction;

    @ManyToMany
    List<Test> tests;

    @Enumerated(EnumType.STRING)
    private MetricType type;

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


    public abstract TestRunMetricResult calculate();

}
