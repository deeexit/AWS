package com.bs.vp;

//import java.util.Date;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewStyle;
import com.jjoe64.graphview.LineGraphView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

public class graph_Setup_2 extends Activity {
	
	private DatePicker dobPicker;
	private DatePicker dobPicker2;
	public GraphViewSeries graph2;
	
	public void onCreate (Bundle savedinstance) {
		super.onCreate(savedinstance);
		setContentView(R.layout.graph_setup_2);
	}
	
	
	public void getData(View view) {
	
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
	
	HttpClient client = new DefaultHttpClient();
	//HttpPost post = new HttpPost("http://bajta.kicks-ass.net/test.pl?date1="+ day.toString() + month.toString() + year.toString());
	HttpGet get = new HttpGet("http://bajta.kicks-ass.net/test.pl?date1="+ day.toString() + month.toString() + year.toString());
	
	try {
		ByteArrayOutputStream outstream = new ByteArrayOutputStream();
		
		HttpResponse response = client.execute(get);
		String result = EntityUtils.toString(response.getEntity());
		
		Toast jee = Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG);
		jee.show();
		
		ArrayList strings = new ArrayList();
		String[] mString = (String[]) strings.toArray(new String[strings.size()]);
		
		String[] separated =  result.split("\n");
		int kuga = separated.length;
		StringBuilder hihi = new StringBuilder();
		String[] test; 
		for (int x = 1; x < kuga; x++) {
			test = separated[x].split("\\|");
			//Toast mhm = Toast.makeText(getApplicationContext(), String.valueOf(test.length), Toast.LENGTH_LONG);
			//mhm.show();
			hihi.append(test[0] + " -- " + test[1]+ " ");
		}
		
	//	  graph2 = new GraphViewSeries (new GraphViewData [] {
	//		new GraphViewData (Integer.parseInt(separated[0]),Integer.parseInt(separated[1])),
	//		new GraphViewData (Integer.parseInt(separated[2]),Integer.parseInt(separated[3])),
	//		new GraphViewData (Integer.parseInt(separated[4]), Integer.parseInt(separated[5]))
	//	});
		Toast grr = Toast.makeText(getApplicationContext(),String.valueOf(kuga) + " " + hihi, Toast.LENGTH_LONG);
		grr.show();

        //separated[1]; 
		
		
		
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
	
}
