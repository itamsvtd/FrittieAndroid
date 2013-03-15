package com.example.frittiepackage;

import java.util.ArrayList;
import java.util.List;

import com.cloudmine.api.SimpleCMObject;
import com.cloudmine.api.rest.callbacks.ObjectModificationResponseCallback;
import com.cloudmine.api.rest.response.ObjectModificationResponse;
import com.example.frittiepackage.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class New_Task extends Activity {

	String userid;
	private String username;
	private String locationname;

	private List<Object> requestpeople;
	private List<Object> acceptedpeople;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new__task);
		username = getIntent().getStringExtra("username");
		userid = getIntent().getStringExtra("id");
		locationname = getIntent().getStringExtra("locationname");
		Toast.makeText(New_Task.this, userid, Toast.LENGTH_LONG).show();
		Toast.makeText(New_Task.this, username, Toast.LENGTH_LONG).show();
		Button create = (Button) findViewById(R.id.button31);

		create.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				EditText note = (EditText) findViewById(R.id.editText34);
				EditText duedate = (EditText) findViewById(R.id.editText31);
				EditText description = (EditText) findViewById(R.id.editText32);
				EditText category = (EditText) findViewById(R.id.editText33);
				requestpeople = new ArrayList<Object>();
				acceptedpeople = new ArrayList<Object>();
				CheckBox completed = (CheckBox) findViewById(R.id.checkBox1);
				String username = getIntent().getStringExtra("username");

				SimpleCMObject task = new SimpleCMObject();
				task.add("creator",username);
				task.add("userid", userid);
				task.add("note", note.getText().toString());
				task.add("time", duedate.getText().toString());
				task.add("description", description.getText().toString());
				task.add("category", category.getText().toString());
				task.add("requestpeople",requestpeople);
				task.add("acceptedpeople",acceptedpeople);
				task.add("activitylocation",locationname);
				if (completed.isChecked()) {
					task.add("completed", 1);
				}else
				{
					task.add("completed", 0);
				}


				task.save(new ObjectModificationResponseCallback() {
					public void onCompletion(ObjectModificationResponse response) {
						System.out.println("task was saved: " + response.wasSuccess());
					}
				});

				Intent intent = new Intent(New_Task.this, Task.class);
				Intent myIntent= getIntent(); // gets the previously created intent
				userid = myIntent.getStringExtra("userid");
				intent.putExtra("userid", userid);
				intent.putExtra("username", username);
				intent.putExtra("locationname", locationname);
				
				startActivity(intent);      
				finish();
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
