package com.github.eljah.tulpar.model.metric;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by ilya on 29.02.16.
 */
@Entity
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    Date date;
    Long value;

}
