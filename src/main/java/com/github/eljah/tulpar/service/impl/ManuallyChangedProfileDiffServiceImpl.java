package com.github.eljah.tulpar.service.impl;

import com.github.eljah.tulpar.annotation.LocalShell;
import com.github.eljah.tulpar.model.Test;
import com.github.eljah.tulpar.model.enums.ProfileDiffType;
import com.github.eljah.tulpar.model.profile.Profile;
import com.github.eljah.tulpar.model.profile.ProfileDiff;
import com.github.eljah.tulpar.repository.ProfileRepository;
import com.github.eljah.tulpar.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ilya on 01.03.16.
 */

@Service("man")
public class ManuallyChangedProfileDiffServiceImpl extends ProfileDiffServiceImpl {
    @Override
    public String doAction(ProfileDiff profileDiff) {
        return profileDiff.getAction();
    }

    @Override
    public int getType() {
        return ProfileDiffType.PROFILE_DIFF_MANUAL.getValue();
    }
}
