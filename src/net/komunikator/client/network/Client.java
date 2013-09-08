package net.komunikator.client.network;

import android.app.Activity;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;
import de.root1.simon.SimonUnreferenced;
import de.root1.simon.annotation.SimonRemote;
import net.komunikator.shared.network.ClientCallbackInterface;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 06.09.13
 * Time: 18:09
 * To change this template use File | Settings | File Templates.
 */
@SimonRemote(value = {ClientCallbackInterface.class})
public class Client implements ClientCallbackInterface, SimonUnreferenced {
    private Activity activity;

    public Client(Activity activity) {
        this.activity = activity;
    }

    private void prepareLooper() {
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
    }

    @Override
    public void toast(final String message) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i("Client", "Server toast:" + message);
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void unreferenced() {
        NetworkConnection.getInstance().disconnect();
        toast("Connection to server closed");
    }
}
