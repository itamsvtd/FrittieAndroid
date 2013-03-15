package com.example.frittiepackage;

import com.cloudmine.api.CMObject;
import com.cloudmine.api.SimpleCMObject;
import com.cloudmine.api.rest.CMStore;
import com.cloudmine.api.rest.callbacks.Callback;
import com.cloudmine.api.rest.callbacks.ObjectModificationResponseCallback;
import com.cloudmine.api.rest.response.CMObjectResponse;
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

public class Edit extends Activity {

	static String userid;
	static String taskdesc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);
		Intent myIntent= getIntent(); // gets the previously created intent
		userid = myIntent.getStringExtra("userid");
		taskdesc = myIntent.getStringExtra("taskdesc");
		/*
		Toast.makeText(getApplicationContext(), userid, Toast.LENGTH_LONG).show();
		Toast.makeText(getApplicationContext(), taskdesc, Toast.LENGTH_LONG).show();
		*/
		CMStore store = CMStore.getStore();

		String searchQuery = "[description = \"" + taskdesc + "\"]";

		store.loadApplicationObjectsSearch(searchQuery, new Callback<CMObjectResponse>() {

			@Override
			public long getStartTime() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public void onCompletion(CMObjectResponse arg0) {
				EditText note = (EditText) findViewById(R.id.editText1);
				EditText duedate = (EditText) findViewById(R.id.editText2);
				EditText description = (EditText) findViewById(R.id.editText3);
				EditText category = (EditText) findViewById(R.id.editText4);
				for(CMObject object : arg0.getObjects()){
					SimpleCMObject task = (SimpleCMObject) object;
					note.setText(task.getString("note"));
					duedate.setText(task.getString("duedate"));
					description.setText(task.getString("description"));
					category.setText(task.getString("category"));
					task.delete();
				}
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
		
		Button edit = (Button) findViewById(R.id.button1);
		
		edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText note = (EditText) findViewById(R.id.editText1);
				EditText duedate = (EditText) findViewById(R.id.editText2);
				EditText description = (EditText) findViewById(R.id.editText3);
				EditText category = (EditText) findViewById(R.id.editText4);

				CheckBox completed = (CheckBox) findViewById(R.id.checkBox01);

				SimpleCMObject task = new SimpleCMObject();
				task.add("creator", userid);
				task.add("note", note.getText().toString());
				task.add("duedate", duedate.getText().toString());
				task.add("description", description.getText().toString());
				task.add("category", category.getText().toString());
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

				Intent intent = new Intent(Edit.this, Task.class);
				Intent myIntent= getIntent(); // gets the previously created intent
				userid = myIntent.getStringExtra("userid");
				intent.putExtra("userid", userid);
				startActivity(intent);      
				finish();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit, menu);
		Intent myIntent= getIntent(); // gets the previously created intent
		userid = myIntent.getStringExtra("userid");
		taskdesc = myIntent.getStringExtra("taskdesc");
		return true;
	}

}
