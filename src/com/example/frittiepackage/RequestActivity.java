package com.example.frittiepackage;

import java.util.ArrayList;
import java.util.List;

import com.cloudmine.api.CMObject;
import com.cloudmine.api.SimpleCMObject;
import com.cloudmine.api.rest.CMStore;
import com.cloudmine.api.rest.callbacks.Callback;
import com.cloudmine.api.rest.response.CMObjectResponse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class RequestActivity extends Activity
{
	private String creator;
	private String note;
	private String username;
	
	private ArrayList<String> listactivities;
	private ArrayList<String> listcreators;
	private ArrayList<String> listnotes;
	
	protected void onCreate(Bundle icicle)

    {
		  super.onCreate( icicle);
		  listactivities= getIntent().getStringArrayListExtra("listactivities");
		  listcreators= getIntent().getStringArrayListExtra("listcreators");
		  listnotes= getIntent().getStringArrayListExtra("listnotes");
		  username = getIntent().getStringExtra("username");
		  
		  
		  setContentView(R.layout.activity_request_activity);
		  creator=getIntent().getStringExtra("creator");
		  note =getIntent().getStringExtra("note");
		  Toast.makeText(getBaseContext(),creator ,Toast.LENGTH_LONG).show();
		  Toast.makeText(getBaseContext(),note ,Toast.LENGTH_LONG).show();
		  Button yes= (Button) findViewById(R.id.yesactivity); 
		  Button no= (Button) findViewById(R.id.noactivity);
		  yes.setOnClickListener(new OnClickListener()
		  {

			@Override
			public void onClick(View arg0) 
			{
				String searchQuery = "[creator= \"" + creator + "\" ,note = \"" + note + "\"]";
				CMStore store = CMStore.getStore();
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

							SimpleCMObject task = (SimpleCMObject) object;
							String activity = "";
							activity = activity + "note: "+task.getString("note") +"\n";
							
							String newnote = task.getString("note");
							String newcreator = task.getString("creator");
							String userid = task.getString("userid");
							String time = task.getString("time");
							String description = task.getString("description");
							String category = task.getString("category");
							String activitylocation = task.getString("activitylocation");
							Integer completed = task.getInteger("completed");
							List<Object> requestpeople = task.getList("requestpeople");
							List<Object> acceptedpeople = task.getList("acceptedpeople");
							requestpeople.add(username);
							task.delete();
							
							task.add("creator",newcreator);
							task.add("userid", userid);
							task.add("note", newnote);
							task.add("time", time);
							task.add("description", description);
							task.add("category",category);
							task.add("requestpeople",requestpeople);
							task.add("acceptedpeople",acceptedpeople);
							task.add("activitylocation",activitylocation);
							task.add("completed", completed);
							task.save();
							Intent intent = new Intent(RequestActivity.this,Extend_List_Activity.class);
							 intent.putExtra("listactivities", listactivities);
			            	  intent.putExtra("listcreators", listcreators);
			            	  intent.putExtra("listnotes",listnotes);
			            	  intent.putExtra("username",username);
							startActivity(intent);   
							//finish();
						}
						
						  
					}
					
					@Override
					public long getStartTime() {
						// TODO Auto-generated method stub
						return 0;
					}
				});
				
			}
			  
		  });
		  no.setOnClickListener(new OnClickListener()
		  {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(RequestActivity.this,Extend_List_Activity.class);
				 intent.putExtra("listactivities", listactivities);
           	  intent.putExtra("listcreators", listcreators);
           	  intent.putExtra("listnotes",listnotes);
				
				startActivity(intent);   
			}
			  
		  });
		  
    }
}
