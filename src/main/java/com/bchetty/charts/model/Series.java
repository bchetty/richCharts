package com.bchetty.charts.model;

import java.util.List;

/**
 *
 * @author Babji Prashanth, Chetty
 */
public class Series {
    private String name;
    private List<Long> data;

    public Series() {}

    public Series(String name, List<Long> data) {
        this.name = name;
        this.data = data;
    }
}
