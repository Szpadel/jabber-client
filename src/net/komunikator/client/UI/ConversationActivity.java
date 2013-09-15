package net.komunikator.client.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import net.komunikator.client.Contacts;
import net.komunikator.client.R;
import net.komunikator.client.UI.adapter.MessagesArrayAdapter;
import net.komunikator.client.entities.Conversation;

import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 31.05.13
 * Time: 03:12
 * To change this template use File | Settings | File Templates.
 */
public class ConversationActivity extends Activity {
    public static final String EXTRA_CONTACT_ID = "net.komunikator.client.UI.ConversationActivity.contact_id";
    Conversation conversation;
    ListView messagesListView;
    EditText sendEditText;
    TextView contactName;
    TextView statusDescription;
    ImageView status;

    Observer updateList = new Observer() {
        @Override
        public void update(Observable observable, Object data) {
            ConversationActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((MessagesArrayAdapter) messagesListView.getAdapter()).notifyDataSetChanged();
                }
            });
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        conversation.addObserver(updateList);
    }

    @Override
    protected void onPause() {
        super.onPause();
        conversation.deleteObserver(updateList);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation);

        messagesListView = (ListView) findViewById(R.id.messages);
        sendEditText = (EditText) findViewById(R.id.message);
        contactName = (TextView) findViewById(R.id.contact);
        status = (ImageView) findViewById(R.id.status);
        statusDescription = (TextView) findViewById(R.id.statusDescription);

        Intent intent = getIntent();
        Contacts contacts = Contacts.getInstance();
        int contactId = intent.getIntExtra(EXTRA_CONTACT_ID, 0);
        conversation = contacts.getContact(contactId).getConversation();

        messagesListView.setAdapter(new MessagesArrayAdapter(this,
                R.layout.messages_contact_message, conversation.getMessages()));
        contactName.setText(conversation.getContact().getName());
        status.setImageResource(conversation.getContact().getStatusResource());
        statusDescription.setText(conversation.getContact().getStatusDescription());
    }

    public void onSend(View v) {
        conversation.sendMessage(sendEditText.getText().toString());
        sendEditText.setText("");
        ((MessagesArrayAdapter) messagesListView.getAdapter()).notifyDataSetChanged();
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(sendEditText.getWindowToken(), 0);
    }
}