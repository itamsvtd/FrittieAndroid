package com.example.frittiepackage;

import java.util.ArrayList;

import com.cloudmine.api.CMObject;
import com.cloudmine.api.SimpleCMObject;
import com.cloudmine.api.rest.CMStore;
import com.cloudmine.api.rest.callbacks.Callback;
import com.cloudmine.api.rest.response.CMObjectResponse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class GetListLocation extends Activity
{
	private ArrayList<String> listlocations;
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		listlocations =  new ArrayList<String>();
		CMStore store = CMStore.getStore();
    	String searchQuery = "[ObjectType= \"" + "Location" + "\"]";
		
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
					
					SimpleCMObject user = (SimpleCMObject) object;
					listlocations.add(user.getString("LocationName"));
					/*
					Toast.makeText(getBaseContext(),user.getString("Locationaddress") , 
							Toast.LENGTH_LONG).show();
					*/
					//finish();
				}
				
				
				  
				  Intent intent = new Intent(GetListLocation.this,ListViewExample.class);
              	  intent.putStringArrayListExtra("listlocations",listlocations);
              	  String username = getIntent().getStringExtra("username");
              	  String userid = getIntent().getStringExtra("userid");
              	  intent.putExtra("username",username); 
              	  intent.putExtra("userid",userid); 
                  startActivity(intent);   
                 
				
				  
			}
			
			@Override
				public long getStartTime() 
					{
					// TODO Auto-generated method stub
					return 0;
					}
				});
	
	}
}
