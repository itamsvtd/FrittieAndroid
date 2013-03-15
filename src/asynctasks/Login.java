package asynctasks;

import com.cloudmine.api.CMObject;
import com.cloudmine.api.SimpleCMObject;
import com.cloudmine.api.rest.CMStore;
import com.cloudmine.api.rest.callbacks.CMObjectResponseCallback;
import com.cloudmine.api.rest.response.CMObjectResponse;
import android.os.AsyncTask;

public class Login extends AsyncTask<Void, Void, Void> {

	private String ID;
	private String Password;

	public Login(String iD, String password) {
		super();
		ID = iD;
		Password = password;
	}
	// Find this in your developer console
	private static final String APP_ID = "664697f9aea840578283875d427c7eca";
	// Find this in your developer console
	private static final String API_KEY = "8cd1d434c17949dc97ad75d38f8cd838";
	@Override
	protected Void doInBackground(Void... arg0) {
		CMStore store = CMStore.getStore();
		String searchQuery = "[id = \"" + ID + "\",password = \"" + Password + "\"]";
		store.loadApplicationObjectsSearch(searchQuery, new CMObjectResponseCallback() {
			public void onCompletion(CMObjectResponse response) {
				for(CMObject object : response.getObjects()){
					// only hondas are returned
					SimpleCMObject user = (SimpleCMObject) object;
				}
			}
		});
		return null;
	}

}
