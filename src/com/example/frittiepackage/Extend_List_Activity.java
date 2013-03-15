package com.example.frittiepackage;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class Extend_List_Activity extends Activity 
{
	ArrayAdapter<String> adapter ;
	private ListView lview;
	private ArrayList<String> listactivities;
	private ArrayList<String> listcreators;
	private ArrayList<String> listnotes;
	private ArrayList<String> listrequestpeople;
	private String username;
	protected void onCreate(Bundle icicle)

    {
		  super.onCreate( icicle);
		  setContentView(R.layout.activity_extend_list);
		 // Intent intent = getIntent();
		  listactivities= getIntent().getStringArrayListExtra("listactivities");
		  listcreators= getIntent().getStringArrayListExtra("listcreators");
		  listnotes= getIntent().getStringArrayListExtra("listnotes");
		  listrequestpeople = getIntent().getStringArrayListExtra("listrequestpeople");
		  username = getIntent().getStringExtra("username");
		  
		  lview = (ListView) findViewById(R.id.listView1);
		  adapter = new ArrayAdapter<String>(Extend_List_Activity.this,R.layout.activity_extend_list,R.id.activityname, listactivities);	  
		  lview.setAdapter(adapter);  
		  
		
		  
          lview.setOnItemClickListener(new OnItemClickListener() {
              @Override
				public void onItemClick(AdapterView<?> l, View view, int position,
                      long id) {
            	     
            	
               	String creator = listcreators.get(position);
               	String note = listnotes.get(position);
              	if (!creator.equals(username))
              	{
            	  Intent intent = new Intent(Extend_List_Activity.this,RequestActivity.class);
            	  intent.putExtra("creator",creator);
            	  intent.putExtra("note",note);
            	  intent.putExtra("username",username);
            	  intent.putExtra("listactivities", listactivities);
            	  intent.putExtra("listcreators", listcreators);
            	  intent.putExtra("listnotes",listnotes);
            	  intent.putExtra("listrequestpeople", listrequestpeople);
                  startActivity(intent); 
              	}
              	else
              	{
              		
              	  Intent intent = new Intent(Extend_List_Activity.this,AcceptedActivity.class);
              	  intent.putExtra("creator",creator);
              	  intent.putExtra("note",note);
              	  intent.putExtra("username",username);
              	  intent.putExtra("listactivities", listactivities);
              	  intent.putExtra("listcreators", listcreators);
              	  intent.putExtra("listnotes",listnotes);
              	  intent.putExtra("listrequestpeople", listrequestpeople);
                  startActivity(intent); 
              	}
              }
          });
    }
}
