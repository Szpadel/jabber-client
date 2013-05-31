package net.komunikator.client.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import net.komunikator.client.Connection;
import net.komunikator.client.R;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 27.05.13
 * Time: 15:19
 * To change this template use File | Settings | File Templates.
 */
public class ConnectToMyServerActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect_to_my_server);
    }

    public void connect(View v) {
        Connection conn = Connection.getInstance();

        EditText ipEditText = (EditText) findViewById(R.id.connect_ip);
        conn.setServerAddress(ipEditText.getText().toString());

        EditText passEditText = (EditText) findViewById(R.id.connect_pass);
        conn.setPassword(passEditText.getText().toString());

        // TODO: try connect

        Intent intent = new Intent(this, ContactsListActivity.class);
        startActivity(intent);

    }
}