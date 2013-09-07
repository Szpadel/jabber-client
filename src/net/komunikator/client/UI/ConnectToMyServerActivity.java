package net.komunikator.client.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import net.komunikator.client.R;
import net.komunikator.client.network.NetworkConnection;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 27.05.13
 * Time: 15:19
 * To change this template use File | Settings | File Templates.
 */
public class ConnectToMyServerActivity extends Activity {

    EditText ipEditText;
    EditText passEditText;
    EditText deviceNameEditText;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect_to_my_server);

        ipEditText = (EditText) findViewById(R.id.connect_ip);
        passEditText = (EditText) findViewById(R.id.connect_pass);
        deviceNameEditText = (EditText) findViewById(R.id.connect_device_name);

        ipEditText.setText(getPreferences(MODE_PRIVATE).getString("server_ip", ""));
        deviceNameEditText.setText(getPreferences(MODE_PRIVATE).getString("device_name", "Android"));
    }

    public void connect(View v) {

        String ip = ipEditText.getText().toString();
        String deviceName = deviceNameEditText.getText().toString();
        String password = passEditText.getText().toString();

        (new Login(this)).execute(ip, deviceName, password);
    }

    private class Login extends AsyncTask<String, Void, Void> {
        boolean success = false;
        String errorMsg;
        Activity activity;
        NetworkConnection networkConnection;
        String ip;
        String deviceName;

        private Login(Activity activity) {
            this.activity = activity;
        }

        @Override
        protected Void doInBackground(String... params) {
            ip = params[0];
            deviceName = params[1];
            String password = params[2];

            if (networkConnection.isLoggedIn()) {
                networkConnection.disconnect();
            }

            if (!networkConnection.connect(ip)) {
                errorMsg = "Connection failed";
                return null;
            }
            if (!networkConnection.login(deviceName, password)) {
                errorMsg = "Login failed";
                return null;
            }
            success = true;
            return null;
        }

        @Override
        protected void onPreExecute() {
            networkConnection = NetworkConnection.getInstance();
            networkConnection.setActivity(activity);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (success) {
                getPreferences(MODE_PRIVATE).edit().putString("server_ip", ip);
                getPreferences(MODE_PRIVATE).edit().putString("device_name", deviceName);

                Intent intent = new Intent(activity, ContactsListActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(activity, errorMsg, Toast.LENGTH_LONG).show();
            }
        }
    }
}