package fragments.series;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.telerik.android.sdk.R;
import com.telerik.widget.chart.engine.databinding.FieldNameDataPointBinding;
import com.telerik.widget.chart.engine.series.combination.ChartSeriesCombineMode;
import com.telerik.widget.chart.visualization.cartesianChart.RadCartesianChartView;
import com.telerik.widget.chart.visualization.cartesianChart.axes.CategoricalAxis;
import com.telerik.widget.chart.visualization.cartesianChart.axes.LinearAxis;
import com.telerik.widget.chart.visualization.cartesianChart.series.categorical.BarSeries;

import java.util.ArrayList;
import java.util.Random;

import activities.ExampleFragment;

/**
 * Created by ginev on 11/19/2014.
 */
public class StackBarSeriesFragment extends Fragment implements ExampleFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FrameLayout rootView = (FrameLayout)inflater.inflate(R.layout.fragment_stack_bar_series, container, false);
        rootView.addView(this.createChart());
        return rootView;
    }

    private RadCartesianChartView createChart(){
        //Create the Chart View
        RadCartesianChartView chart = new RadCartesianChartView(this.getActivity());

        LinearAxis verticalAxis = new LinearAxis();
        //The values in the linear axis will not have values after the decimal point.
        verticalAxis.setLabelFormat("%.0f");

        CategoricalAxis horizontalAxis = new CategoricalAxis();
        chart.setVerticalAxis(verticalAxis);
        chart.setHorizontalAxis(horizontalAxis);

        for (int i = 0; i < 3; i++) {
            //Create the bar series and attach axes and value bindings.
            BarSeries barSeries = new BarSeries();

            //We want to stack the different bar series.
            barSeries.setCombineMode(ChartSeriesCombineMode.STACK);

            barSeries.setValueBinding(new FieldNameDataPointBinding("value"));
            barSeries.setCategoryBinding(new FieldNameDataPointBinding("category"));


            //Bind series to data
            barSeries.setData(this.getData());
            //Add series to chart
            chart.getSeries().add(barSeries);
        }

        return chart;
    }

    private ArrayList<DataEntity> getData(){
        Random numberGenerator = new Random();
        ArrayList<DataEntity> result = new ArrayList<DataEntity>(8);

        for (int i = 0; i < 8; i++){
            DataEntity entity = new DataEntity();
            entity.value = numberGenerator.nextInt(10) + 1;
            entity.category = "Item " + i;
            result.add(entity);
        }

        return result;
    }

    @Override
    public String title() {
        return "Stack bar series";
    }

    public class DataEntity{
        public String category;
        public int value;
    }
}
