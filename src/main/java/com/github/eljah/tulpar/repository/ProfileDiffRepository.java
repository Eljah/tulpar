package com.github.eljah.tulpar.repository;

import com.github.eljah.tulpar.model.Test;
import com.github.eljah.tulpar.model.profile.Profile;
import com.github.eljah.tulpar.model.profile.ProfileDiff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ilya on 01.03.16.
 */
public interface ProfileDiffRepository  extends JpaRepository<ProfileDiff, Long> {

    List<ProfileDiff> findByProfiles(Profile profile);

    List<ProfileDiff> findByName(List<String> names);

}
