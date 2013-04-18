package asynctasks;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.cloudmine.api.CMAndroidSocial;
import com.cloudmine.api.CMUser;
import com.cloudmine.api.rest.CMStore;
import com.cloudmine.api.rest.CMSocial;
import com.cloudmine.api.rest.callbacks.CMSocialLoginResponseCallback;
import com.cloudmine.api.rest.response.CMSocialLoginResponse;

public class LoginFacebook extends Activity
{
	    
	 

		
	public void onCreate(Bundle savedInstanceState) 
	{
		Toast.makeText(getBaseContext(), "GOOOO", Toast.LENGTH_LONG).show();
		
		CMAndroidSocial.loginThroughSocial(CMSocial.Service.FACEBOOK, this, new CMSocialLoginResponseCallback() {
		    public void onCompletion(CMSocialLoginResponse response) {
		        System.out.println("Authenticated to: " + response.getUser().getAuthenticatedServices());
		        System.out.println("Logged in? " + response.getSessionToken().isValid());
		 
		        // configure the store with the user so that it can perform user-centric operations
		        CMStore.getStore().setUser(response.getUser());
		    }
		 
		    @Override
		    public void onFailure(Throwable t, String msg) {
		        System.out.println("Oh no we failed! " + t.getMessage());
		    }
		});
		
		
		
	}
	
	
}
