package com.bs.vp;

//import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.DatePicker;

public class graph_Setup_2 extends Activity {
	
	private DatePicker dobPicker;
	private DatePicker dobPicker2;
	
	public void onCreate (Bundle savedinstance) {
		super.onCreate(savedinstance);
		setContentView(R.layout.graph_setup_2);
	}
	
	
	public void getData(View view) {
	
	//Toast wtf = Toast.makeText(getApplicationContext(), "WTF!!!", Toast.LENGTH_LONG);
	//wtf.show();
	dobPicker = (DatePicker) findViewById (R.id.datePicker1);
	dobPicker2 = (DatePicker) findViewById (R.id.datePicker2);
	
	// First Date field
	Integer month = dobPicker.getMonth();
	Integer day = dobPicker.getDayOfMonth();
	Integer year = dobPicker.getYear();
	
	// Second Date field
	
	
	// mamica je na luni :)
	
	// fsipanc pa na tleh!!
	
	Integer month2 = dobPicker2.getMonth();
	Integer day2 = dobPicker2.getDayOfMonth();
	Integer year2 = dobPicker2.getYear();
	
	StringBuilder sb = new StringBuilder();
	sb.append("Date1: " + day.toString() + "/" + month.toString() + "/" + year.toString() +
			"  " + "Date2: " + day2.toString() + "/" + month2.toString() + "/" + year2.toString());
	
	Toast msg = Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_LONG);
	msg.show();
	
	Intent intent = new Intent(graph_Setup_2.this, Graphs.class);
	startActivity(intent);
	}
	
}
