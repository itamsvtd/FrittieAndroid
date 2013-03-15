package com.example.frittiepackage;

import java.util.ArrayList;

import com.cloudmine.api.CMObject;
import com.cloudmine.api.SimpleCMObject;
import com.cloudmine.api.rest.CMStore;
import com.cloudmine.api.rest.callbacks.Callback;
import com.cloudmine.api.rest.response.CMObjectResponse;
import com.example.frittiepackage.R;


import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewExample extends Activity 
{
              
             
				private ArrayList<String> listlocations;
             
                private ListView lview;
               
                EditText locationinput;
                int textlength=0;
                ArrayAdapter<String> adapter ;
               
                
                @Override
				public void onCreate(Bundle icicle)

                {
                	
                                super.onCreate(icicle);
                                setContentView(R.layout.activity_list_view_example);
                                
                                listlocations= getIntent().getStringArrayListExtra("listlocations");
                                
                                lview = (ListView) findViewById(R.id.listview);
                                locationinput = (EditText) findViewById(R.id.locationinput);
                                System.out.println(R.layout.list_item);
                                //adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listlocations);
                                adapter = new ArrayAdapter<String>(this,R.layout.list_item, R.id.location_name,listlocations);
                                lview.setAdapter(adapter);
                                 
                               // Intent intentID = getIntent();
                               // setContentView(R.layout.tabbed_main);
                               // String name = intentID.getStringExtra("username");
                               
                               // Toast.makeText(this, name, Toast.LENGTH_LONG).show();
                                Button personbutton = (Button) findViewById(R.id.personalpage);
                                personbutton.setOnClickListener(new OnClickListener()
                                {

									@Override
									public void onClick(View arg0)
									{
										// TODO Auto-generated method stub
										String username = getIntent().getStringExtra("username");
	                                  	Intent intent = new Intent(ListViewExample.this,UserPage.class);
	                                  	intent.putExtra("username",username);  
	                                    startActivity(intent);   
									}
                                	
                                });
                                
                                lview.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
									public void onItemClick(AdapterView<?> l, View view, int position,
                                            long id) {
                                       
                                        
                                     	String selection = l.getItemAtPosition(position).toString();
                                    	
                                  	  Intent intent = new Intent(ListViewExample.this,TabTest.class);
                                  	  intent.putExtra("locationname",selection);
                                  	 String username = getIntent().getStringExtra("username");
                                 	  intent.putExtra("username",username);
                                        startActivity(intent);   
                                    }
                                });
                                
                                locationinput.addTextChangedListener(new TextWatcher() {
                                	 
                                    @Override
                                    public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                                     
                                        ListViewExample.this.adapter.getFilter().filter(cs);
                                       
                                    }
                         
                                    @Override
                                    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                            int arg3) {
                                        // TODO Auto-generated method stub
                         
                                    }
                         
                                    @Override
                                    public void afterTextChanged(Editable arg0) {
                                        // TODO Auto-generated method stub
                                    }
                                });
                                
                }
                
              
 }
  
