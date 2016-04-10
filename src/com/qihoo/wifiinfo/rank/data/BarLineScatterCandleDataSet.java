package com.qihoo.wifiinfo.rank.data;

import java.util.ArrayList;

import android.graphics.Color;

public abstract class BarLineScatterCandleDataSet extends DataSet {

    /** default highlight color */
    protected int mHighLightColor = Color.rgb(255, 187, 115);

    public BarLineScatterCandleDataSet(ArrayList<? extends Entry> yVals, String label) {
        super(yVals, label);
    }

    /**
     * Sets the color that is used for drawing the highlight indicators. Dont
     * forget to resolve the color using getResources().getColor(...) or
     * Color.rgb(...).
     * 
     * @param color
     */
    public void setHighLightColor(int color) {
        mHighLightColor = color;
    }

    /**
     * Returns the color that is used for drawing the highlight indicators.
     * 
     * @return
     */
    public int getHighLightColor() {
        return mHighLightColor;
    }
}