package net.komunikator.client.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    Button loginButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect_to_my_server);

        ipEditText = (EditText) findViewById(R.id.connect_ip);
        passEditText = (EditText) findViewById(R.id.connect_pass);
        deviceNameEditText = (EditText) findViewById(R.id.connect_device_name);
        loginButton = (Button) findViewById(R.id.connect_login_button);

        ipEditText.setText(getPreferences(MODE_PRIVATE).getString("server_ip", ""));
        deviceNameEditText.setText(getPreferences(MODE_PRIVATE).getString("device_name", "Android"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        NetworkConnection networkConnection = NetworkConnection.getInstance();
        if (networkConnection.isLoggedIn()) {
            openContactActivity();
        }
    }

    public void connect(View v) {

        String ip = ipEditText.getText().toString();
        String deviceName = deviceNameEditText.getText().toString();
        String password = passEditText.getText().toString();

        (new Login(this)).execute(ip, deviceName, password);
    }

    private void openContactActivity() {
        Intent intent = new Intent(this, ContactsListActivity.class);
        startActivity(intent);
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
            loginButton.setEnabled(false);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            loginButton.setEnabled(true);
            if (success) {
                getPreferences(MODE_PRIVATE).edit().putString("server_ip", ip)
                        .putString("device_name", deviceName).apply();
                openContactActivity();
            } else {
                Toast.makeText(activity, errorMsg, Toast.LENGTH_LONG).show();
            }
        }
    }
}