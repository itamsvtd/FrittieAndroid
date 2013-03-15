package com.example.frittiepackage;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

import android.app.Activity;
import android.os.Bundle;

public class GetGoogleMap extends MapActivity
{
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_google_map);
	        /*
	        MapView mapView = (MapView) findViewById(R.id.mapview1);
	        mapView.setTraffic(true); 
	        mapView.setBuiltInZoomControls(true);
	        */
	    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}