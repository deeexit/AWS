package com.bs.vp;

//import java.util.Date;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;



import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import com.bs.vp.Graphs;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewStyle;
import com.jjoe64.graphview.LineGraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;

public class graph_Setup_2 extends Activity {
	
	private DatePicker dobPicker;
	private DatePicker dobPicker2;
	//public Graphs graph2;
	public Graphs graphx;
	//public StringBuilder hihi;
	public ArrayAdapter arr1;
	public String[] datax = null;
	public String[] datay = null;
	public GraphViewData[] data;
	public GraphView graphView;
	private ProgressDialog dprogress;
	
	
	public void onCreate (Bundle savedinstance) {
		super.onCreate(savedinstance);
		setContentView(R.layout.graph_setup_2);
	}
	
	
	
	private class DownloadGraphData extends AsyncTask <String, Void, String> {

		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String response = "";
			
			getData();
			
			
			return response;
		}

	    protected void onPostExecute (String response) {
	    	
	    	
	           setContentView (R.layout.graph_view);
	           graphView.addSeries(new GraphViewSeries("Temperatura",new GraphViewStyle(Color.rgb(255, 0, 0), 3),data));// data
	           graphView.setShowLegend(true);
	           graphView.setViewPort(1, data.length);
	           graphView.setScalable(true);
	           graphView.setScrollable(true);
	           LinearLayout layout = (LinearLayout) findViewById(R.id.graph1);
	           layout.addView(graphView);
	           dprogress.dismiss();
	    }
		
	}
	
	
	
	public void getData() {
		//ArrayAdapter<String> arr1 = new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1);
	//Toast wtf = Toast.makeText(getApplicationContext(), "WTF!!!", Toast.LENGTH_LONG);
	//wtf.show();
	dobPicker = (DatePicker) findViewById (R.id.datePicker1);
	dobPicker2 = (DatePicker) findViewById (R.id.datePicker2);
	
	// Set onClickListener
	
	
	// First Date field
	Integer month = dobPicker.getMonth();
	Integer day = dobPicker.getDayOfMonth();
	Integer year = dobPicker.getYear();
	
	// Second Date field
	
	Integer month2 = dobPicker2.getMonth();
	Integer day2 = dobPicker2.getDayOfMonth();
	Integer year2 = dobPicker2.getYear();
	
	
	StringBuilder sb = new StringBuilder();
	sb.append("Date1: " + day.toString() + "/" + month.toString() + "/" + year.toString() +
			"  " + "Date2: " + day2.toString() + "/" + month2.toString() + "/" + year2.toString());
	
	//Toast msg = Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_LONG);
	//msg.show();
	
	
	// HTTP stuff
	//month = month + 1;
	//month2 = month2 + 1;
	String mesec = month.toString();
	String mesec2 = month2.toString();
	
	
	if (month < 10) { mesec = "0" + month.toString(); }
	if (month2 < 10) {  mesec2 = "0" + month2.toString(); }
	HttpClient client = new DefaultHttpClient();
	//HttpPost post = new HttpPost("http://bajta.kicks-ass.net/test.pl?date1="+ day.toString() + month.toString() + year.toString());
	HttpGet get = new HttpGet("http://bajta.kicks-ass.net/test.pl?date1="+ day.toString() + "-" +  mesec + "-" + year.toString() + 
			"&date2="+day2.toString() + "-" + mesec2 + "-" + year2.toString());
	
	try {
		ByteArrayOutputStream outstream = new ByteArrayOutputStream();
		
		HttpResponse response = client.execute(get);
		String result = EntityUtils.toString(response.getEntity());
		
		//Toast jee = Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG);
		//jee.show();
		
		
		//String[] mString = (String[]) strings.toArray(new String[strings.size()]);
		
	
		String[] separated =  result.split("\n");
		
		
		
		//ListView list1 = new ListView (this);
		//list1.setAdapter(arr1);
		//setContentView(list1);
		//list1.showContextMenu();
		
		int kuga = separated.length;
		StringBuilder hihi = new StringBuilder();
		String[] test; 
		
		
		// Create graph
		
		
		//GraphViewData[] data = new GraphViewData[kuga-1];
	    data = new GraphViewData[kuga-1]; 
		for (int x = 1; x < kuga; x++) {
			test = separated[x].split("\\|");
			//Toast mhm = Toast.makeText(getApplicationContext(), String.valueOf(test.length), Toast.LENGTH_LONG);
			//mhm.show();
			hihi.append(test[0] + " -- " + test[1]+ " ");
			//Toast mamamia = Toast.makeText(getApplicationContext(), test[0] + " " + test[1],Toast.LENGTH_LONG );
			//mamamia.show();
					double value1 = Double.parseDouble(test[0]);
					double value2 = Double.parseDouble(test[1]);
					
					value2 = (value2 - 32) * 5 / 9;
					
					data[x-1] = new GraphViewData(value1, value2);
			
			
			
		}
		//int num = 160;
        
        //double v=0;
        //for (int x = 0; x<160; x++) {
        //	v += 0.2;
        //	data[x] = new GraphViewData(x, Math.sin(v));
        	//data[x] = new GraphViewData(Integer.getInteger(jao.arr1.getItem(x).toString()),Integer.getInteger(jao.arr1.getItem(x).toString()));
        //}
		
		final java.text.DateFormat dateTimeFormatter = DateFormat.getTimeFormat(getApplicationContext());
		
		graphView = new LineGraphView(getApplicationContext(), "Testni grafek") {
		@Override
		protected String formatLabel (double value, boolean isValueX) {
			if (isValueX) {
				return dateTimeFormatter.format(new Date((long) value * 1000));
				//return dateTimeFormatter.format(new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(new java.util.Date((long) (value * 1000))));
			} else {
				return super.formatLabel(value, isValueX);
			}
			
			}
		};
		
		
		// graphView = new LineGraphView(this , "GraphViewDemo");
        
		
		
//		HttpResponse response = client.execute(post);
		
	} catch (ClientProtocolException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	//Toast test = Toast.makeText(getApplicationContext(), , Toast.LENGTH_LONG);
	
	//Intent intent = new Intent(graph_Setup_2.this, Graphs.class);
	//startActivity(intent);
	}
	
	public void runTask(View view) {
		
		dprogress = ProgressDialog.show(this, "VP Info", "Rišem...");
		new Thread() {
			
		public void run() {
			try {
				new DownloadGraphData().execute(new String[] { "WTF!!"});
			} catch (Exception e) {
				Log.e("VP Info:", "Fail at get graph data... ");
			}
		 } 
		}.start();
		
	}
	
}
