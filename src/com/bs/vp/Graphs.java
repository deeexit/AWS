package com.bs.vp;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;

import android.app.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.bs.vp.graph_Setup_2;



//import bs.bami.control.R;


    
	
	




public class Graphs extends Activity {
    
//	public graph_Setup_2 graphx;
	//public GraphViewSeries graphx;
    public graph_Setup_2 jao;
	
	// @SuppressWarnings("null")
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_view);
        
        
        Toast hihi = Toast.makeText(getApplicationContext(), "hihi", Toast.LENGTH_LONG);
        hihi.show();
     // init example series data
       // GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
        //          new GraphViewData(1, 2.0d)
        //          , new GraphViewData(2, 1.5d)
        //          , new GraphViewData(3, 2.5d)
        //          , new GraphViewData(4, 1.0d)
        //});
       // GraphViewSeries graphx = new GraphViewSeries (new GraphViewData[] { new GraphViewData(1,1) });
        int num = 160;
        GraphViewData[] data = new GraphViewData[num];
       
        double v=0;
        for (int x = 0; x<160; x++) {
        	v += 0.2;
        	data[x] = new GraphViewData(x, Math.sin(v));
        	//data[x] = new GraphViewData(Integer.getInteger(jao.arr1.getItem(x).toString()),Integer.getInteger(jao.arr1.getItem(x).toString()));
        }
    
        
        GraphView graphView = new LineGraphView(
              this // context
              , "GraphViewDemo" // heading
        );
        graphView.addSeries(new GraphViewSeries(data)); // data
        graphView.setViewPort(2, 2);
        graphView.setScalable(true);
        graphView.setScrollable(true);
        LinearLayout layout = (LinearLayout) findViewById(R.id.graph1);
        layout.addView(graphView);
     
       
        
       
    	
      
        
    	
        
        
    }

}
