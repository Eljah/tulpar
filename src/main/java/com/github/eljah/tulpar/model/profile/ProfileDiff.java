package com.github.eljah.tulpar.model.profile;

import com.github.eljah.tulpar.annotation.RemoteShell;
import com.github.eljah.tulpar.model.enums.ProfileDiffType;
import com.github.eljah.tulpar.model.enums.UserRole;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by ilya on 29.02.16.
 */

@Entity
@Inheritance
@DiscriminatorColumn(name="type")
public abstract class ProfileDiff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    String name;
    String description;
    String action;

    //@Enumerated(EnumType.STRING)
    @Column(name="type", insertable = false, updatable = false)
    private String type;

    public List<Profile> getProfiles() {
        return profiles;
    }

    @ManyToMany
    private List<Profile> profiles;


    public abstract void setName(String description);

    public abstract void setDescription(String description);

    public abstract void setAction(String action);


    public abstract String getName();

    public abstract String getDescription();

    public abstract String getAction();

    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    public String getType() {
        return type+"";
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



}
