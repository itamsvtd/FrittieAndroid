package com.example.frittiepackage;


import com.example.frittiepackage.R;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;
 

public class Action extends Fragment {

	 @Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	   Bundle savedInstanceState) {
	  View myFragmentView = inflater.inflate(R.layout.activity_action, container, false);
	  Intent i = new Intent(getActivity(), Task.class);
	  String username = getActivity().getIntent().getExtras().getString("username");
	  String userid = getActivity().getIntent().getExtras().getString("userid");
	  String locationname = getActivity().getIntent().getExtras().getString("locationname");
	  i.putExtra("username",username); 
	  i.putExtra("userid",userid); 
	  i.putExtra("locationname",locationname);
	  //Toast.makeText(Action.this, userid, Toast.LENGTH_LONG).show();
		//Toast.makeText(Action.this, username, Toast.LENGTH_LONG).show();
	  startActivity(i);
	  
      
	  return myFragmentView;
	 }

	}