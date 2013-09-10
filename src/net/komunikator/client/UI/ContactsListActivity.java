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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_list);
        contactsList = (ListView) findViewById(R.id.contacts_view);
        contacts = Contacts.getInstance();
        ContactsArrayAdapter contactsArrayAdapter = new ContactsArrayAdapter(this, R.layout.contact_row, contacts.getContacts());
        contactsArrayAdapter.setNotifyOnChange(true);
        contactsList.setAdapter(contactsArrayAdapter);

        final ContactsListActivity _this = this;
        contactsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(_this, ConversationActivity.class);
                intent.putExtra(ConversationActivity.EXTRA_CONTACT_ID,
                        ((Contact) parent.getAdapter().getItem(position)).getId());
                _this.startActivity(intent);
            }
        });
    }

}