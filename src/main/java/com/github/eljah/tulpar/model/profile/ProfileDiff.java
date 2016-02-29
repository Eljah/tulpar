package com.github.eljah.tulpar.model.profile;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by ilya on 29.02.16.
 */

@Entity
public abstract class ProfileDiff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    String name;
    String description;
    String action;

    public abstract void setName(String description);

    public abstract void setDescription(String description);

    public abstract void setAction(String action);


    public abstract String getName();

    public abstract String getDescription();

    public abstract String getAction();



}
