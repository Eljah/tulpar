package com.github.eljah.tulpar.model.profile;

import com.github.eljah.tulpar.annotation.LocalShell;
import com.github.eljah.tulpar.annotation.RemoteShell;
import com.github.eljah.tulpar.model.enums.ProfileDiffType;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by ilya on 29.02.16.
 */
@Entity
@DiscriminatorValue("2")
public class LocalChangedProfileDiff extends ProgrammaticallyChangedProfileDiff {

    @LocalShell
    public String getAction() {
        return this.action;
    }

    public String getType() {
        return "2";
    }

}
