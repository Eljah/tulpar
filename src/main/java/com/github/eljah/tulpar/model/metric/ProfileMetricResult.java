package com.github.eljah.tulpar.model.metric;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by eljah32 on 3/6/2016.
 */

@Entity
@DiscriminatorValue("Profile")
public class ProfileMetricResult extends Result {
}
