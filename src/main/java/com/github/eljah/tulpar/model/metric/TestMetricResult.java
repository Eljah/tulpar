package com.github.eljah.tulpar.model.metric;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by ilya on 29.02.16.
 */
@Entity
@DiscriminatorValue("Test")
public class TestMetricResult extends Result {

    @OneToMany
    Set<Data> averagedDataSet;
}
