package com.qihoo.wifiinfo.rank.utils;

public class PointD {

    public double x;
    public double y;

    public PointD(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /** returns a string representation of the object */
    public String toString() {
        return "PointD, x: " + x + ", y: " + y;
    }
}