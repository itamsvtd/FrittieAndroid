package com.example.frittiepackage;


import com.example.frittiepackage.R;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
 

public class ShowList extends Fragment {

	 @Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
	   Bundle savedInstanceState) {
		 
		String username = getActivity().getIntent().getExtras().getString("username");
		String locationname = getActivity().getIntent().getExtras().getString("locationname");

	  View myFragmentView = inflater.inflate(R.layout.activity_show_list, container, false);
	  Intent i = new Intent(getActivity(), ActivityByDate.class);
	  i.putExtra("username",username);
	  i.putExtra("locationname", locationname);
	  startActivity(i);
	  
	  return myFragmentView;
	 }

	}