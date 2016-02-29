package com.github.eljah.tulpar.model.profile;

/**
 * Created by ilya on 29.02.16.
 */
public abstract class ProgrammaticallyChangedProfileDiff extends ProfileDiff {
    String name;
    String description;
    String action;


    @Override
    public void setName(String name) {
        this.name= name;
    }

    @Override
    public void setDescription(String description) {
        this.description=description;
    }

    @Override
    public void setAction(String action) {
        this.action=action;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String getAction() {
        return this.action;
    }
}
