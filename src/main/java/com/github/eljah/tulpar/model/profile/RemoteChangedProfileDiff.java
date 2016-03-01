package com.github.eljah.tulpar.model.profile;

import com.github.eljah.tulpar.annotation.RemoteShell;
import com.github.eljah.tulpar.aop.ShellExecutionAspect;
import com.github.eljah.tulpar.model.enums.ProfileDiffType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by ilya on 29.02.16.
 */
@Entity
@DiscriminatorValue("3")
@Configurable
public class RemoteChangedProfileDiff extends ProgrammaticallyChangedProfileDiff {

    public String getType() {
        return "3";   }


     @RemoteShell
    public String getAction() {
        return this.action;
    }
}
