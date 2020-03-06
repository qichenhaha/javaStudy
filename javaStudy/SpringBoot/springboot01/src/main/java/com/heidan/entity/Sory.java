package com.heidan.entity;

import lombok.Data;

/**
 * Create by heidan on 2020/1/7 12:02
 */
@Data
public class Sory implements Comparable<Sory>{
    private int id;

    private String name;

    private double longitude;

    private double latitude;

    private double distance;

    @Override
    public int compareTo(Sory o) {

        return (int) (this.distance-o.distance);
    }
}
