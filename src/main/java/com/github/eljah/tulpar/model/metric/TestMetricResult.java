package com.github.eljah.tulpar.model.metric;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ilya on 29.02.16.
 */
@Entity
@DiscriminatorValue("Test")
public class TestMetricResult extends Result {

}
