package com.example.frittiepackage;

import java.util.ArrayList;

import com.cloudmine.api.CMObject;
import com.cloudmine.api.SimpleCMObject;
import com.cloudmine.api.rest.CMStore;
import com.cloudmine.api.rest.callbacks.Callback;
import com.cloudmine.api.rest.response.CMObjectResponse;
import com.example.frittiepackage.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Task extends Activity {

	private String username;
	private String userid;
	private String locationname;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		username = getIntent().getStringExtra("username");
		userid = getIntent().getStringExtra("id");
		locationname = getIntent().getStringExtra("locationname");
		Toast.makeText(Task.this, userid, Toast.LENGTH_LONG).show();
		Toast.makeText(Task.this, username, Toast.LENGTH_LONG).show();
		Toast.makeText(Task.this, locationname, Toast.LENGTH_LONG).show();
		setContentView(R.layout.activity_task);

		ListView listView = (ListView) findViewById(R.id.listView21);

		listView.setSelector(R.drawable.bande_verte);

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				/*************************************************/

				final String description = arg0.getItemAtPosition(arg2).toString();

				Button edit_task = (Button) findViewById(R.id.button22);

				edit_task.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						Toast.makeText(Task.this, "CLICK EDIT", Toast.LENGTH_LONG).show();

						Intent myIntent= getIntent(); // gets the previously created intent
						userid = myIntent.getStringExtra("userid");
						String username = myIntent.getStringExtra("username");
		              	  
						Intent intent = new Intent(Task.this, Edit.class);
						Toast.makeText(getApplicationContext(), userid, Toast.LENGTH_LONG).show();
						intent.putExtra("userid", userid);
						intent.putExtra("username", username);
						intent.putExtra("taskdesc", description);
						startActivity(intent);      
						finish();

					}

				});

				Button delete_task = (Button) findViewById(R.id.button23);

				delete_task.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						CMStore store = CMStore.getStore();

						Toast.makeText(getApplicationContext(), description, Toast.LENGTH_LONG).show();

						String searchQuery = "[description = \"" + description + "\"]";

						store.loadApplicationObjectsSearch(searchQuery, new Callback<CMObjectResponse>() {

							@Override
							public long getStartTime() {
								// TODO Auto-generated method stub
								return 0;
							}

							@Override
							public void onCompletion(CMObjectResponse arg0) {
								for(CMObject object : arg0.getObjects()){
									SimpleCMObject task = (SimpleCMObject) object;
									Toast.makeText(getApplicationContext(), task.getString("description"), Toast.LENGTH_LONG).show();
									task.delete();
								}
								Intent myIntent= getIntent(); // gets the previously created intent
								userid = myIntent.getStringExtra("userid");

								Intent intent = new Intent(Task.this, Task.class);
								//Toast.makeText(getApplicationContext(), userid, Toast.LENGTH_LONG).show();
								intent.putExtra("userid", userid);
								intent.putExtra("username", username);
								startActivity(intent);      
								finish();

							}

							@Override
							public void onFailure(Throwable arg0, String arg1) {
								// TODO Auto-generated method stub

							}

							@Override
							public void setStartTime(long arg0) {
								// TODO Auto-generated method stub

							}

						});

					}

				});
				/*************************************************/
			}

		});

		Button new_task = (Button) findViewById(R.id.button21);

		new_task.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(Task.this, New_Task.class);
				
				//intent.putExtra("userid", userid);
				
				//intent.putExtra("username", username);
				intent.putExtra("userid", userid);
				intent.putExtra("username", username);
				intent.putExtra("locationname", locationname);
				startActivity(intent);      
				finish();
			}

		});

		CMStore store = CMStore.getStore();

		Intent myIntent= getIntent(); // gets the previously created intent
		userid = myIntent.getStringExtra("userid");

		//Toast.makeText(getApplicationContext(), userid, Toast.LENGTH_LONG).show();

		String searchQuery = "[userid = \"" + userid + "\"]";

		store.loadApplicationObjectsSearch(searchQuery, new Callback<CMObjectResponse>() {

			@Override
			public long getStartTime() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public void onCompletion(CMObjectResponse arg0) {
				ListView listView = (ListView) findViewById(R.id.listView21);
				ArrayList<String> values = new ArrayList<String>();

				for(CMObject object : arg0.getObjects()){
					SimpleCMObject task = (SimpleCMObject) object;
					values.add(task.getString("description"));
				}
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
						android.R.layout.simple_list_item_single_choice, values);
				listView.setAdapter(adapter);
			}

			@Override
			public void onFailure(Throwable arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void setStartTime(long arg0) {
				// TODO Auto-generated method stub

			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.task, menu);
		Intent myIntent= getIntent(); // gets the previously created intent
		userid = myIntent.getStringExtra("userid");

		//Toast.makeText(getApplicationContext(), ID, Toast.LENGTH_LONG).show();

		return true;
	}

}
