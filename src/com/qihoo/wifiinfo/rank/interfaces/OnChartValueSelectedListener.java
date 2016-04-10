package com.qihoo.wifiinfo.rank.interfaces;

import com.qihoo.wifiinfo.rank.data.Entry;


public interface OnChartValueSelectedListener {

    /**
     * Called when a value has been selected inside the chart.
     * 
     * @param e The selected Entry.
     * @param dataSetIndex The index in the datasets array of the data object
     *            the Entrys DataSet is in.
     */
    public void onValueSelected(Entry e, int dataSetIndex);

    /**
     * Called when nothing has been selected or an "un-select" has been made.
     */
    public void onNothingSelected();
}