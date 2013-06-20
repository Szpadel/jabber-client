package net.komunikator.client.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import net.komunikator.client.R;
import net.komunikator.client.ServerConnection;

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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect_to_my_server);

        ipEditText = (EditText) findViewById(R.id.connect_ip);
        passEditText = (EditText) findViewById(R.id.connect_pass);

        ipEditText.setText(getPreferences(MODE_PRIVATE).getString("server_ip", ""));
    }

    public void connect(View v) {
        ServerConnection conn = ServerConnection.getInstance();

        conn.setServerAddress(ipEditText.getText().toString());
        conn.setPassword(passEditText.getText().toString());

        getPreferences(MODE_PRIVATE).edit().putString("server_ip", conn.getServerAddress());

        ServerConnection.connect();

        Intent intent = new Intent(this, ContactsListActivity.class);
        startActivity(intent);

    }
}