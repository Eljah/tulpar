package com.github.eljah.tulpar.model.profile;

import com.github.eljah.tulpar.annotation.RemoteShell;

/**
 * Created by ilya on 29.02.16.
 */
public class RemoteChangedProfileDiff extends ProgrammaticallyChangedProfileDiff {
    @RemoteShell
    public String getAction() {
        return this.action;
    }
}
