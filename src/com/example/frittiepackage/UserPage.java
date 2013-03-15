package com.example.frittiepackage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserPage extends Activity {
	 protected void onCreate(Bundle savedInstanceState)
	 {
	    	super.onCreate(savedInstanceState);
	    	setContentView(R.layout.activity_user_page);
	    	String username = getIntent().getStringExtra("username");
	    	Toast.makeText(getBaseContext(),username , Toast.LENGTH_LONG).show();
	    	TextView displayname =(TextView) findViewById(R.id.username);
	        displayname.setTextSize(30);
	        displayname.setText(username+"'s Page");
	    	
	    	Button createnewlocation = (Button) findViewById(R.id.CreateNewLocation);
	    	createnewlocation.setOnClickListener(new OnClickListener()
             {

					@Override
					public void onClick(View arg0)
					{
						// TODO Auto-generated method stub
						String username = getIntent().getStringExtra("username");
						String id = getIntent().getStringExtra("id");
						Intent intent = new Intent(UserPage.this,CreateNewLocation.class);
						intent.putExtra("username",username);  
						intent.putExtra("id",id); 
						startActivity(intent);   
					}
             	
             });
	 }
}
