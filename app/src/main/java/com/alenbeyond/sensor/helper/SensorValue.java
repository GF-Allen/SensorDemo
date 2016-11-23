package com.alenbeyond.sensor.helper;

/**
 * Created by alen on 16/11/23.
 */

public class SensorValue {

    private int id;

    private String x_value;
    private String y_value;
    private String z_value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getX_value() {
        return x_value;
    }

    public SensorValue setX_value(String x_value) {
        this.x_value = x_value;
        return this;
    }

    public String getY_value() {
        return y_value;
    }

    public SensorValue setY_value(String y_value) {
        this.y_value = y_value;
        return this;
    }

    public String getZ_value() {
        return z_value;
    }

    public SensorValue setZ_value(String z_value) {
        this.z_value = z_value;
        return this;
    }
}
