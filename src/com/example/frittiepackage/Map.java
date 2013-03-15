package com.example.frittiepackage;


import com.example.frittiepackage.R;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Map extends Fragment {

 @Override
 public View onCreateView(LayoutInflater inflater, ViewGroup container,
   Bundle savedInstanceState) {
  View myFragmentView = inflater.inflate(R.layout.activity_map, container, false);
  Intent i = new Intent(getActivity(), GetGoogleMap.class);
  startActivity(i);
  return myFragmentView;
 }

}