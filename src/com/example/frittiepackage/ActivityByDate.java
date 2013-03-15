package com.example.frittiepackage;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;
import com.cloudmine.api.CMApiCredentials;
import com.cloudmine.api.CMObject;
import com.cloudmine.api.SimpleCMObject;
import com.cloudmine.api.rest.CMStore;
import com.cloudmine.api.rest.callbacks.Callback;
import com.cloudmine.api.rest.response.CMObjectResponse;

public class ActivityByDate extends ListActivity {
	private static final String APP_ID = "664697f9aea840578283875d427c7eca";
	private static final String API_KEY = "8cd1d434c17949dc97ad75d38f8cd838";
	ArrayAdapter<String> adapter ;
	private ArrayList<String> listactivities;
	private ArrayList<String> listcreators;
	private ArrayList<String> listnotes;
	private ArrayList<String> listrequestpeople;
	private ListView lview;
	private String username;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_by_date);
	  listactivities =  new ArrayList<String>();
	 
	 // lview = (ListView) findViewById(R.id.listActivityView);
	   username = getIntent().getStringExtra("username");
	  Toast.makeText(this, username, Toast.LENGTH_LONG).show();
	  List<String>products = new ArrayList<String>();
	  
	  products.add("asdads");
	
	  products.add("aljlj");
	  
	  /* Calendar View 
	  */
	  
	  CalendarView dateget;
	  dateget= (CalendarView) findViewById(R.id.calendarView1);
	  CMApiCredentials.initialize(APP_ID, API_KEY, getApplicationContext());

	  dateget.setOnDateChangeListener(new OnDateChangeListener() {
		  
	  
	  
		@Override
		public void onSelectedDayChange(CalendarView view, int year, int month,
				int dayOfMonth) {
			 listactivities =  new ArrayList<String>();
			 listcreators = new ArrayList<String>();
			 listnotes = new ArrayList<String>();
			 listrequestpeople = new ArrayList<String>();
			// TODO Auto-generated method stub
			String gettime =Integer.toString(dayOfMonth)+"/"+Integer.toString(month)+"/"+Integer.toString(year);
			String activitylocation = getIntent().getStringExtra("locationname");
			Toast.makeText(getBaseContext(),gettime , 
				Toast.LENGTH_LONG).show();
			
			
			CMStore store = CMStore.getStore();

			
			String searchQuery = "[time= \"" + gettime + "\" ,activitylocation = \"" + activitylocation + "\"]";
			
			store.loadApplicationObjectsSearch(searchQuery, new Callback<CMObjectResponse>() {

				@Override
				public void setStartTime(long arg0) {

				}

				@Override
				public void onFailure(Throwable arg0, String arg1) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onCompletion(CMObjectResponse arg0) {

					//Toast.makeText(getApplicationContext(), arg0.getMessageBody(), Toast.LENGTH_LONG).show();

					for(CMObject object : arg0.getObjects()){

						SimpleCMObject obj = (SimpleCMObject) object;
						String activity = "";
						activity = activity + "note: "+obj.getString("note") +"\n";
						activity = activity + "creator: "+obj.getString("creator") +"\n";
						activity = activity + "description: "+obj.getString("description") +"\n";
						activity = activity + "requestpeople: " + obj.getList("requestpeople") +"\n";
						activity = activity + "acceptedpeople" + obj.getList("acceptedpeople") +"\n";
						activity = activity + "\n";
						listactivities.add(activity);
						listcreators.add(obj.getString("creator"));
						listnotes.add(obj.getString("note"));
						String x = String( obj.getList("requestpeople") );
						listrequestpeople.add(x);
						
						//finish();
					}
					/*
					 Toast.makeText(getBaseContext(),listactivities.get(0) , 
								Toast.LENGTH_LONG).show();

					 Toast.makeText(getBaseContext(),listactivities.get(1) , 
								Toast.LENGTH_LONG).show();
					*/
					if (listactivities.size() >0)
					{
					  Intent intent = new Intent(ActivityByDate.this,Extend_List_Activity.class);
                  	  //intent.putExtra("restaurantname",listactivities);
					
	                
					  
					  intent.putStringArrayListExtra("listactivities",listactivities);
                  	  intent.putStringArrayListExtra("listcreators",listcreators);
                  	  intent.putStringArrayListExtra("listnotes",listnotes);
                  	  intent.putStringArrayListExtra("listrequestpeople",listrequestpeople);
                  	  intent.putExtra("username",username);
                      startActivity(intent);   
					}
					  
				}
				
				private String String(List<Object> list) {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public long getStartTime() {
					// TODO Auto-generated method stub
					return 0;
				}
			});
			
			
		}
		
    });
	 // listactivities.add("aaskdjlj");
	 
	  
	}
}
