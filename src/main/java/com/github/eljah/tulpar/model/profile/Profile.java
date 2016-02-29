package com.github.eljah.tulpar.model.profile;

import com.github.eljah.tulpar.model.Test;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ilya on 29.02.16.
 */
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToMany
    private List<ProfileDiff> profileDiffs;

    @OneToMany
    private List<Test> tests;

}
