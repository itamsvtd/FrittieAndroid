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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AcceptedActivity extends Activity
{
	private String creator;
	private String note;
	private String username;
	
	
	private ArrayList<String> listactivities;
	private ArrayList<String> listcreators;
	private ArrayList<String> listnotes;
	private ListView lview;
	private EditText password;
	private String blah;
	protected void onCreate(Bundle icicle)

    {
		  super.onCreate( icicle);
		  setContentView(R.layout.activity_accept_activity);
		  listactivities= getIntent().getStringArrayListExtra("listactivities");
		  listcreators= getIntent().getStringArrayListExtra("listcreators");
		  listnotes= getIntent().getStringArrayListExtra("listnotes");
		  username = getIntent().getStringExtra("username");
		  
		  password = (EditText) findViewById(R.id.editperson);
		  
		

		 
		  creator=getIntent().getStringExtra("creator");
		  note =getIntent().getStringExtra("note");
		  Toast.makeText(getBaseContext(),creator ,Toast.LENGTH_LONG).show();
		  Toast.makeText(getBaseContext(),note ,Toast.LENGTH_LONG).show();
		  lview = (ListView) findViewById(R.id.listView1);
		 
		  Button yes= (Button) findViewById(R.id.acceptbutton); 
		  Button no= (Button) findViewById(R.id.backbutton);
		 
		  
		  yes.setOnClickListener(new OnClickListener()
		  {

			@Override
			public void onClick(View arg0) 
			{
				 blah = password.getText().toString() ;
				//  Toast.makeText(AcceptedActivity.this,blah ,Toast.LENGTH_LONG).show();
				
			
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
							//Toast.makeText(AcceptedActivity.this,acceptedperson.getText().toString() ,Toast.LENGTH_LONG).show();
							
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
							acceptedpeople.add( blah);
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
							Intent intent = new Intent(AcceptedActivity.this,Extend_List_Activity.class);
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
				Intent intent = new Intent(AcceptedActivity.this,Extend_List_Activity.class);
				 intent.putExtra("listactivities", listactivities);
           	  intent.putExtra("listcreators", listcreators);
           	  intent.putExtra("listnotes",listnotes);
				
				startActivity(intent);   
				
			}
			
		  });
		
   
    }
	public String GetBlah()
	{
		return this.blah;
	}
}
