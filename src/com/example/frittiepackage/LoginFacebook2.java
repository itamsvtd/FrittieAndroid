package com.example.frittiepackage;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.cloudmine.api.CMAndroidSocial;
import com.cloudmine.api.CMUser;
import com.cloudmine.api.rest.CMStore;
import com.cloudmine.api.rest.CMSocial;
import com.cloudmine.api.rest.callbacks.CMSocialLoginResponseCallback;
import com.cloudmine.api.rest.callbacks.CreationResponseCallback;
import com.cloudmine.api.rest.response.CMSocialLoginResponse;
import com.cloudmine.api.rest.response.CreationResponse;

public class LoginFacebook2 extends Activity
{
	    
	 
	private static final String APP_ID = "664697f9aea840578283875d427c7eca";
	// Find this in your developer console
	private static final String API_KEY = "8cd1d434c17949dc97ad75d38f8cd838";

		
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		//Toast.makeText(getBaseContext(), "GOOOO", Toast.LENGTH_LONG).show();

		CMAndroidSocial.loginThroughSocial(CMSocial.Service.LINKEDIN, this, new CMSocialLoginResponseCallback() {
		    public void onCompletion(CMSocialLoginResponse response) {
		        System.out.println("Authenticated to: " + response.getUser().getAuthenticatedServices());
		        System.out.println("Logged in? " + response.getSessionToken().isValid());
		 
		        // configure the store with the user so that it can perform user-centric operations
		        CMStore.getStore().setUser(response.getUser());
		        //Toast.makeText(getBaseContext(), response.getUser().getEmail(), Toast.LENGTH_LONG).show();
		       // email = response.getUser().getEmail() ;
		      Toast.makeText(getBaseContext(), "xzcxzc1", Toast.LENGTH_LONG).show();
		      
		    }
		    
		    @Override
		    public void onFailure(Throwable t, String msg) {
		        System.out.println("Oh no we failed! " + t.getMessage());
		        Toast.makeText(getBaseContext(), "xzcxzc2", Toast.LENGTH_LONG).show();
		    }
		    
		    
		});
		
		//Toast.makeText(getBaseContext(), email, Toast.LENGTH_LONG).show();
		
		
		/*
		 final CMUser user2 = new CMUser("vuthanhduc92@yahoo.com", "calidream");
		user2.createUser(new CreationResponseCallback() {
		    public void onCompletion(CreationResponse response) {
		        CMAndroidSocial.linkToUser(CMSocial.Service.FACEBOOK, LoginFacebook2.this, user2, new CMSocialLoginResponseCallback() {
		            public void onCompletion(CMSocialLoginResponse response) {
		                System.out.println("Authenticated to: " + response.getUser().getAuthenticatedServices());
		                System.out.println("Logged in? " + response.getSessionToken().isValid());
		            }
		 
		            @Override
		            public void onFailure(Throwable t, String msg) {
		                System.out.println("Oh no we failed! " + t.getMessage());
		            }
		        });
		    }
		});
		*/
		
		
		CMAndroidSocial.clearSessionCookies(this);
		
		//Intent intent = new Intent(LoginFacebook2.this, GetListLocation.class);
		//startActivity(intent);  
	}
	
	
}
