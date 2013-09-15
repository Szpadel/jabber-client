package net.komunikator.client.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import net.komunikator.client.Contacts;
import net.komunikator.client.R;
import net.komunikator.client.UI.adapter.ContactsArrayAdapter;
import net.komunikator.client.entities.Contact;

import java.util.Observable;
import java.util.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 30.05.13
 * Time: 02:34
 * To change this template use File | Settings | File Templates.
 */
public class ContactsListActivity extends Activity {

    ListView contactsList;
    Contacts contacts;

    private Observer updateList = new Observer() {
        @Override
        public void update(Observable observable, Object data) {
            ContactsListActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ((ContactsArrayAdapter) contactsList.getAdapter()).notifyDataSetChanged();
                }
            });
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        Contacts.getInstance().addObserver(updateList);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Contacts.getInstance().deleteObserver(updateList);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_list);
        contactsList = (ListView) findViewById(R.id.contacts_view);
        contacts = Contacts.getInstance();
        ContactsArrayAdapter contactsArrayAdapter = new ContactsArrayAdapter(this, R.layout.contact_row, contacts.getContacts());
        contactsList.setAdapter(contactsArrayAdapter);

        contactsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ContactsListActivity.this, ConversationActivity.class);
                intent.putExtra(ConversationActivity.EXTRA_CONTACT_ID,
                        ((Contact) parent.getAdapter().getItem(position)).getId());
                ContactsListActivity.this.startActivity(intent);
            }
        });
    }

}