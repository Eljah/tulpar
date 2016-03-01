package com.github.eljah.tulpar.model.profile;

import com.github.eljah.tulpar.model.enums.ProfileDiffType;

import javax.persistence.*;

/**
 * Created by ilya on 29.02.16.
 */

@Entity
@DiscriminatorValue("1")
public class ManuallyChangedProfileDiff extends ProfileDiff {

    //private ProfileDiffType type=ProfileDiffType.PROFILE_DIFF_MANUAL;

    public String getType() {
        return "1";
    }


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
