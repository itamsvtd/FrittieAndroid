package com.example.frittiepackage;

import com.example.frittiepackage.R;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TabTest extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        //      R.layout.activity_location, R.id.locationinputmore, pizza);
        Intent intent = getIntent();
        
        String username = getIntent().getStringExtra("username");
  	  	intent.putExtra("username",username); 
  	  	String userid = getIntent().getStringExtra("userid");
	  	intent.putExtra("userid",userid);  
        
        setContentView(R.layout.tabbed_main);
        String name = intent.getStringExtra("locationname");
        
        
        //Toast.makeText(this, name, Toast.LENGTH_LONG).show();
        EditText locationnews = (EditText) findViewById(R.id.locationinputmore);
        TextView displayname = new TextView(this);
        displayname.setTextSize(20);
        displayname.setText(name);
        setContentView(displayname);
        
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);  
      
    	 
        	Tab tabActivity = actionBar.newTab();     
            tabActivity.setText("Manage"); 
            tabActivity.setTabListener(new TabListener<Action>(this, "Activity", Action.class));
            //tabActivity.setIcon(R.drawable.activities); 
            actionBar.addTab(tabActivity); 
            
            Tab tabShowList = actionBar.newTab(); 
            tabShowList.setText("Explore"); 
            tabShowList.setTabListener(new TabListener<ShowList>(this, "ShowList", ShowList.class));
            //tabActivity.setIcon(R.drawable.activities); 
            actionBar.addTab(tabShowList); 
            
            Tab tabMap = actionBar.newTab();
            tabMap.setText("Map");
            tabMap.setTabListener(new TabListener<Map>(this, "Map",Map.class));
            //tabMap.setIcon(R.drawable.googlemap);
            actionBar.addTab(tabMap);
            
            Tab tabPicture = actionBar.newTab();  //Setting Tab A      
            tabPicture.setText("Picture"); //Setting Title for Tab A
            tabPicture.setTabListener(new TabListener<Picture>(this, "Picture", Picture.class));
            //tabPicture.setIcon(R.drawable.picture); //Setting an Icon for Tab A
            actionBar.addTab(tabPicture); //Adding tab to the Action Bar
            
            
            
            if (savedInstanceState != null) {
                int savedIndex = savedInstanceState.getInt("SAVED_INDEX");
                getActionBar().setSelectedNavigationItem(savedIndex);
            }
            
        }
    
    public static class TabListener<T extends Fragment> 
    implements ActionBar.TabListener{
    
       private final Activity ActivityInTab;
       private final String myTag;
       private final Class<T> myClass;

       public TabListener(Activity activity, String tag, Class<T> cls) {
           ActivityInTab = activity;
           myTag = tag;
           myClass = cls;
       }

       @Override
       public void onTabSelected(Tab tab, FragmentTransaction ft) {

    	   Fragment myFragment = ActivityInTab.getFragmentManager().findFragmentByTag(myTag);
  
    	   // Check if the fragment is already initialized
    	   if (myFragment == null) 
    	   {
    		// If not, instantiate and add it to the activity
    		   myFragment = Fragment.instantiate(ActivityInTab, myClass.getName());
    		   ft.add(android.R.id.content, myFragment, myTag);
    	   } 
    	   else 
    	   {
    		   // If it exists, simply attach it in order to show it
    		   ft.attach(myFragment);
    	   }
  
 }

 @Override
 		public void onTabUnselected(Tab tab, FragmentTransaction ft) 
 		{
	 		Fragment myFragment = ActivityInTab.getFragmentManager().findFragmentByTag(myTag);
  
	 		if (myFragment != null) 
	 		{
            // Detach the fragment, because another one is being attached
	 			ft.detach(myFragment);
	 		}
  
 		}

 @Override
 		public void onTabReselected(Tab tab, FragmentTransaction ft) 
 		{
	 		// TODO Auto-generated method stub
 		}
    
   }
}