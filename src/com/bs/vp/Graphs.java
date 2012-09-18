package com.bs.vp;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
//import bs.bami.control.R;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewStyle;
import com.jjoe64.graphview.LineGraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;

public class Graphs extends Activity {
    
	public GraphViewSeries graph2;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_view);
        
     // init example series data
        GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
                  new GraphViewData(1, 2.0d)
                  , new GraphViewData(2, 1.5d)
                  , new GraphViewData(3, 2.5d)
                  , new GraphViewData(4, 1.0d)
        });

        GraphView graphView = new LineGraphView(
              this // context
              , "GraphViewDemo" // heading
        );
        graphView.addSeries(exampleSeries); // data
        graphView.setViewPort(1, 2);
        graphView.setScalable(true);
        graphView.setScrollable(true);
        LinearLayout layout = (LinearLayout) findViewById(R.id.graph1);
        layout.addView(graphView);
        
        // test 2 
        //GraphViewSeries graph2 = new GraphViewSeries(new GraphViewData[] {
        //						 new GraphViewData (1,1),
        //						 new GraphViewData (2,2),
        //						 new GraphViewData (3,3),
        //						 new GraphViewData (4,10),
        //						 new GraphViewData (5,25)
        //});
    	
      
        
    	
      //  GraphView graphView2 = new LineGraphView (this, "Graf 2");
      //  graphView2.addSeries(graph2);
      //  graphView2.setViewPort(1, 1);
      //  graphView2.setScalable(true);
      //  LinearLayout layout2 = (LinearLayout) findViewById(R.id.graph2);
      //  layout2.addView(graphView2);
        // Random curve to the first graph
        int num = 150;
    	double v=0;
    	GraphViewData[] data = new GraphViewData[num];
    	for (int i=0; i<num; i++) {
    		v += 0.2;
    		data[i] = new GraphViewData(i, Math.sin(Math.random()*v));
    		}
    	GraphViewSeries series1 = new GraphViewSeries ("Random curve",new GraphViewStyle(Color.rgb(90, 250, 00), 3), data);
        graphView.addSeries(series1);
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.graphs_menu, menu);
        return true;
    }
    
	public boolean onOptionsItemSelected (MenuItem item) {
	
		switch (item.getItemId()) {
	case (R.id.item1):
		Toast simply_red = Toast.makeText(getApplicationContext(), "Prpezdeno!",Toast.LENGTH_LONG);
		simply_red.show();

		
		
		return true;
		
		}

		return true;	

}


}


