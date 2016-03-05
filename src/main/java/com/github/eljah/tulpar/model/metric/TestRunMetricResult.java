package com.github.eljah.tulpar.model.metric;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ilya on 29.02.16.
 */
@Entity
@DiscriminatorValue("TestRun")
public class TestRunMetricResult extends Result{

}
