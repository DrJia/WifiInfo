package com.qihoo.wifiinfo;

import java.util.ArrayList;

import com.qihoo.wifiinfo.rank.data.BarData;
import com.qihoo.wifiinfo.rank.data.BarDataSet;
import com.qihoo.wifiinfo.rank.data.BarEntry;
import com.qihoo.wifiinfo.rank.utils.ColorTemplate;
import com.qihoo.wifiinfo.rank.utils.XLabels;
import com.qihoo.wifiinfo.rank.utils.XLabels.XLabelPosition;
import com.qihoo.wifiinfo.rank.view.BarChart;

import android.app.Activity;
import android.os.Bundle;

public class TestActivity extends Activity {

	private BarChart mChart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);

		mChart = (BarChart) findViewById(R.id.chart1);

		//mChart.setDrawYValues(false);

		//mChart.setUnit(" €");
		//mChart.setDescription("");

		// if more than 60 entries are displayed in the chart, no values will be
		// drawn
		mChart.setMaxVisibleValueCount(60);

		// sets the number of digits for values inside the chart
		mChart.setValueDigits(2);

		// disable 3D
		mChart.set3DEnabled(false);
		// scaling can now only be done on x- and y-axis separately
		//mChart.setPinchZoom(false);

		//mChart.setDrawBarShadow(false);
		
		//mChart.setTouchEnabled(false);

		//mChart.setDrawVerticalGrid(false);
		//mChart.setDrawHorizontalGrid(false);
		//mChart.setDrawGridBackground(false);

		/*XLabels xLabels = mChart.getXLabels();
		xLabels.setPosition(XLabelPosition.BOTTOM);
		xLabels.setCenterXLabelText(true);
		xLabels.setSpaceBetweenLabels(0);*/

		//mChart.setDrawYLabels(false);
		

		// setting data
		// mSeekBarX.setProgress(10);
		// mSeekBarY.setProgress(100);

		// Legend l = mChart.getLegend();
		// l.setPosition(LegendPosition.BELOW_CHART_CENTER);
		// l.setFormSize(8f);
		// l.setFormToTextSpace(4f);
		// l.setXEntrySpace(6f);

		// mChart.setDrawLegend(false);

		ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
		for (int i = 0; i < 10; i++) {
			float mult = 100;
			//float val1 = (float) (Math.random() * mult) + mult / 3;
			float val1 = (float) (Math.random() * mult);
			yVals1.add(new BarEntry((int) val1, i));
		}

		ArrayList<String> xVals = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			xVals.add((int) yVals1.get(i).getVal() + " " + mChart.getUnit());
			//xVals.add((int) yVals1.get(i).getVal() + "");
		}

		BarDataSet set1 = new BarDataSet(yVals1, "Data Set");
		set1.setColors(ColorTemplate.createColors(getApplicationContext(),
				ColorTemplate.VORDIPLOM_COLORS));

		ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
		dataSets.add(set1);

		BarData data = new BarData(xVals, dataSets);

		mChart.setData(data);
		//mChart.invalidate();

		// add a nice and smooth animation
		mChart.animateY(2500);
		//mChart.animateX(2500);
	}

}
