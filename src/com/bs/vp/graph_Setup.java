package com.bs.vp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class graph_Setup extends Activity {
		
	public void onCreate (Bundle savedinstance) {
		super.onCreate(savedinstance);
		setContentView(R.layout.graph_setup);
	}
	
	
	public void getData(View view) {
	
	Intent intent = new Intent(graph_Setup.this, graph_Setup_2.class);
	startActivity(intent);
	}
	
}
