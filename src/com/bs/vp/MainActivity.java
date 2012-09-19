package com.bs.vp;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


import com.bs.vp.R;
//import com.pxr.tutorial.xmltest.Main;
//import com.pxr.tutorial.xmltest.XMLParser;
import com.bs.vp.XMLParser;



import android.os.AsyncTask;
import android.os.Bundle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
//import com.pxr.tutorial.xmltest.Main;
//import com.pxr.tutorial.xmltest.XMLParser;
//import android.sax.Element;


public class MainActivity extends Activity {

	  public String temperatura = "";
	  public String pritisk = "";
	  public String padavine = "";
	  public String vlaga = "";
	  
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toast toast = Toast.makeText(getApplicationContext(),"Hi Sucker! Stisni refresh loool ;)", Toast.LENGTH_SHORT);
        //toast.show();
        
        // Define TextViews
        refreshData();
      
    }
	
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
	public boolean onOptionsItemSelected (MenuItem item) {
		
		
		switch (item.getItemId()) {
		
		case (R.id.menu_settings):
			Toast toast = Toast.makeText(getApplicationContext(),"Hi Sucker! Nastavitve! ;)", Toast.LENGTH_SHORT);
	        toast.show();
	        Log.i("VP info:",item.getTitle().toString());
	        
	        return true;
	        
		case (R.id.item1):
		
			//Toast toast1 = Toast.makeText(getApplicationContext(),"Hi Sucker! Stisni refresh loool ;)", Toast.LENGTH_SHORT);
	        //toast1.show();
	        Log.i("VP info:",item.getTitle().toString());
		TextView temp_view = (TextView) findViewById (R.id.TextView01);
        TextView vlaznost_view = (TextView) findViewById (R.id.TextView02);
        TextView tlak_view = (TextView) findViewById (R.id.TextView03);
        TextView padavine_view = (TextView) findViewById (R.id.TextView04);
		temp_view.setText("");
		vlaznost_view.setText("");
		tlak_view.setText("");
		padavine_view.setText("");
		
		ProgressBar spin11 = (ProgressBar) findViewById(R.id.progressBar1);
		spin11.setVisibility(View.VISIBLE);    
		refreshData();
		//spin11.setVisibility(View.INVISIBLE);
	        return true;
	        
		case (R.id.item2):
			Intent intent = new Intent(MainActivity.this, graph_Setup.class);
			startActivity(intent);
			return true;

		}
				return true;
		
		
	}

	
	public void refreshData () {
		//Toast toast1 = Toast.makeText(this, "Yiii", Toast.LENGTH_LONG);
		//toast1.show();
		
		
		new DownloadXMLTask().execute(new String[] { "WTF!!"});
	  //spin1.setVisibility(View.INVISIBLE);
		
	}
	
	
	// AsyncTask for UI responsivness
	
	private class DownloadXMLTask extends AsyncTask <String, Void, String>{

	//verride
		protected String doInBackground(String...string) {
			
			// TODO Auto-generated method stub
			String response = "";
			String xml = XMLParser.getXML();
	          Document doc = XMLParser.XMLfromString(xml);
	                
	          int numResults = XMLParser.numResults(doc);
	       
	        if((numResults <= 0)){
	        	//Toast.makeText(this, "Ni zadetkov :)", Toast.LENGTH_LONG).show();  
	        	finish();
	        }
	        //ArrayAdapter<String> arr1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1); 
	      
			NodeList nodes = doc.getElementsByTagName("Meritev");
			Log.i("Info: ", String.valueOf(nodes.getLength()));
			for (int i = 0; i < nodes.getLength(); i++) {							
				
				Element e = (Element)nodes.item(i);
				//arr1.add("Datum: " + XMLParser.getValue(e, "Datum"));
				//arr1.add("Ura: " +XMLParser.getValue(e, "Ura"));
				
				//arr1.add("Temperatura: " + XMLParser.getValue(e, "Temperatura"));
				//temp.setText(XMLParser.getValue(e, "Temperatura") + " °C");
				temperatura = XMLParser.getValue(e, "Temperatura") + " °C" ;
				vlaga = XMLParser.getValue(e, "Vlaznost") + " %";
				pritisk = XMLParser.getValue(e,"Pritisk") + " hPa";
				padavine = XMLParser.getValue(e, "Padavine")+ " l/m²";
				
				//arr1.add("Vlažnost: " + XMLParser.getValue(e, "Vlaznost"));
				//vlaznost.setText(XMLParser.getValue(e, "Vlaznost") + " %");
				
				//arr1.add("Pritisk: "+ XMLParser.getValue(e, "Pritisk"));
				//tlak.setText(XMLParser.getValue(e, "Pritisk") + " hPa");
				
				//arr1.add("Veter: " + XMLParser.getValue(e, "Veter"));
				//padavine.setText(XMLParser.getValue(e, "Padavine") + " l/m²");
				//arr1.add("Smer: " + XMLParser.getValue(e, "Smer"));
				//arr1.add("Padavine: " + XMLParser.getValue(e, "Padavine"));
				//arr1.add("Napoved: " + XMLParser.getValue(e, "Napoved"));
				
			}		
			//ListView list1 = (ListView) findViewById(R.id.listView1);
			//list1.setAdapter(arr1);
	        
			return response;
		}
		protected void onPostExecute (String response) {
			
			TextView temp_view = (TextView) findViewById (R.id.TextView01);
	        TextView vlaznost_view = (TextView) findViewById (R.id.TextView02);
	        TextView tlak_view = (TextView) findViewById (R.id.TextView03);
	        TextView padavine_view = (TextView) findViewById (R.id.TextView04);
			temp_view.setText(temperatura);
			vlaznost_view.setText(vlaga);
			tlak_view.setText(pritisk);
			padavine_view.setText(padavine);
			ProgressBar spin11 = (ProgressBar) findViewById(R.id.progressBar1);
			spin11.setVisibility(View.INVISIBLE);
		}


		
	}
	
   
    
}
