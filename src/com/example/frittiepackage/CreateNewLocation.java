package com.example.frittiepackage;

import com.cloudmine.api.SimpleCMObject;
import com.cloudmine.api.rest.callbacks.ObjectModificationResponseCallback;
import com.cloudmine.api.rest.response.ObjectModificationResponse;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateNewLocation extends Activity 
{
	public void onCreate(Bundle savedInstanceState)

    {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_new_location);
		
		
		
		
		
		Button save = (Button) findViewById(R.id.savelocation);

		save.setOnClickListener(new OnClickListener() 
		{
		

		@Override
		public void onClick(View v) 
			{
			// TODO Auto-generated method stub
				SimpleCMObject newlocation = new SimpleCMObject();
				EditText name = (EditText) findViewById(R.id.editname);
				EditText address = (EditText) findViewById(R.id.editaddress);
				EditText description = (EditText) findViewById(R.id.editdescription);
			
				newlocation.add("LocationName", name.getText().toString());
				newlocation.add("Locationaddress", address.getText().toString());
				newlocation.add("Locationdescription", description.getText().toString());
				newlocation.add("ObjectType", "Location");
				newlocation.save(new ObjectModificationResponseCallback() 
				{
					public void onCompletion(ObjectModificationResponse response) 
					{
						Toast.makeText(getBaseContext(),"Your location saved" , 
						Toast.LENGTH_LONG).show();
					}
				});
			}
		
		});
    }
}
