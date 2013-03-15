package com.example.frittiepackage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cloudmine.api.CMApiCredentials;
import com.cloudmine.api.CMObject;
import com.cloudmine.api.SimpleCMObject;
import com.cloudmine.api.rest.CMStore;
import com.cloudmine.api.rest.callbacks.Callback;
import com.cloudmine.api.rest.response.CMObjectResponse;
import com.example.frittiepackage.R;

public class MainActivity extends Activity {

	// Find this in your developer console
	private static final String APP_ID = "664697f9aea840578283875d427c7eca";
	// Find this in your developer console
	private static final String API_KEY = "8cd1d434c17949dc97ad75d38f8cd838";


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		CMApiCredentials.initialize(APP_ID, API_KEY, getApplicationContext());

		Button login = (Button) findViewById(R.id.button01);

		login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				EditText userID = (EditText) findViewById(R.id.editText01);

				EditText password = (EditText) findViewById(R.id.editText02);

				CMStore store = CMStore.getStore();

				//Toast.makeText(getApplicationContext(), userID.getText().toString(), Toast.LENGTH_LONG).show();
				
				String searchQuery = "[username = \"" + userID.getText().toString() + "\",password = \"" + password.getText().toString() + "\"]";

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
							Intent intent = new Intent(MainActivity.this, Task.class);
							Toast.makeText(getApplicationContext(), user.getString("id"), Toast.LENGTH_LONG).show();
							intent.putExtra("userid", user.getString("id"));
							startActivity(intent);      
							finish();
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


		Button register = (Button) findViewById(R.id.button02);

		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
				startActivity(intent);      
				finish();

			}

		});
	}
}
