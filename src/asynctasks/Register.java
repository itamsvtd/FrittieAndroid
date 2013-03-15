package asynctasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.cloudmine.api.SimpleCMObject;
import com.cloudmine.api.rest.callbacks.ObjectModificationResponseCallback;
import com.cloudmine.api.rest.response.ObjectModificationResponse;

import android.os.AsyncTask;

public class Register extends AsyncTask<Void, Void, String> {

	// Find this in your developer console
	private static final String APP_ID = "664697f9aea840578283875d427c7eca";
	// Find this in your developer console
	private static final String API_KEY = "8cd1d434c17949dc97ad75d38f8cd838";

	private String Name;
	private String Password;
	private String ID;
	private String Email;

	public Register(String name, String password,  String email, String iD) {
		super();
		Name = name;
		Password = password;
		ID = iD;
		Email = email;
	}

	@Override
	protected String doInBackground(Void... params) {
		URL url;
		String output = "0";
		try {
			url = new URL("https://api.cloudmine.me/v1/app/" + APP_ID + "/account/create");

			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			request.setDoOutput(true);
			request.setRequestMethod("POST");
			request.setRequestProperty("X-CloudMine-ApiKey", API_KEY);

			request.setRequestProperty("Content-Type", "application/json");

			String input = "{\"credentials\": {" +
					"\"email\": \"" +  Email + "\"," +
					"\"username\": \"" + ID + "\"," +
					"\"password\": \"" + Password + "\"}," +
					"\"profile\": {\"name\": \"" + Name + "\",\"location\": {" +
					"\"__type__\": \"geopoint\"," +
					"\"longitude\": 48.5," +
					"\"latitude\": -78.2}}}";

			OutputStream os = request.getOutputStream();
			os.write(input.getBytes());
			os.flush();

			if (request.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				//System.out.println(request.getResponseCode());
				return "400";
			}
			else
			{
				BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
				String result, line = reader.readLine();
				result = line;
				while((line=reader.readLine())!=null){
					result+=line;
				}
				JSONParser parser=new JSONParser();
				Object obj=parser.parse(result);
				JSONObject array=(JSONObject)obj;
				SimpleCMObject honda = new SimpleCMObject();
				
				honda.add("email", Email);
				honda.add("username", ID);
				honda.add("password", Password);
				honda.add("name", Name);
				honda.add("id", array.get("__id__"));

				output = array.get("__id__").toString();
				
				honda.save(new ObjectModificationResponseCallback() {
					public void onCompletion(ObjectModificationResponse response) {
						System.out.println("User was saved: " + response.wasSuccess());
					}
				});
			}

			request.disconnect();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
	}

	protected void onPreExecute(String[] result) {
		super.onPreExecute();
	}
}
