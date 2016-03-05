package com.github.eljah.tulpar.model.profile;

import com.github.eljah.tulpar.annotation.RemoteShell;
import com.github.eljah.tulpar.model.enums.ProfileDiffType;
import com.github.eljah.tulpar.model.enums.UserRole;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    public Set<Profile> getProfiles() {
        return profiles;
    }

    @ManyToMany(mappedBy = "profileDiffs")
    private Set<Profile> profiles;


    public abstract void setName(String description);

    public abstract void setDescription(String description);

    public abstract void setAction(String action);


    public abstract String getName();

    public abstract String getDescription();

    public abstract String getAction();

    public void setProfiles(Set<Profile> profiles) {
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

    @Override
    public boolean equals(Object o)
    {
        return (this.name.equals(((ProfileDiff)o).name));
    }

    @Override
    public String toString()
    {
        return (this.name);
    }

}
