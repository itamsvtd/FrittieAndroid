package com.example.frittiepackage;

import java.util.concurrent.ExecutionException;

import com.example.frittiepackage.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import asynctasks.Register;

public class RegisterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		Button register = (Button) findViewById(R.id.button11);

		register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				EditText Name = (EditText)findViewById(R.id.editText11);
				EditText Password = (EditText)findViewById(R.id.editText12);
				EditText Email = (EditText)findViewById(R.id.editText13);
				EditText ID = (EditText)findViewById(R.id.editText14);

				Register registration = new Register(Name.getText().toString(),Password.getText().toString(),Email.getText().toString(),ID.getText().toString());

				try {
					String output = registration.execute().get();
					//Toast.makeText(getApplicationContext(), output, Toast.LENGTH_LONG).show();
					if (!output.equals("400")) {
						Intent intent = new Intent(RegisterActivity.this, MainLog.class);
						intent.putExtra("userid", output);
						Toast.makeText(getApplicationContext(), "You registered successfully", Toast.LENGTH_LONG).show();
						startActivity(intent);      
						finish();
					}
					else
					{
						Toast.makeText(getApplicationContext(), "Id already in use or not suitable email", Toast.LENGTH_LONG).show();
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}

}
