package net.komunikator.client.UI.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import net.komunikator.client.R;
import net.komunikator.client.entities.Contact;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 31.05.13
 * Time: 00:49
 * To change this template use File | Settings | File Templates.
 */
public class ContactsArrayAdapter extends ArrayAdapter<Contact> {

    public ContactsArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ContactsArrayAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public ContactsArrayAdapter(Context context, int textViewResourceId, Contact[] objects) {
        super(context, textViewResourceId, objects);
    }

    public ContactsArrayAdapter(Context context, int resource, int textViewResourceId, Contact[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public ContactsArrayAdapter(Context context, int textViewResourceId, List<Contact> objects) {
        super(context, textViewResourceId, objects);
    }

    public ContactsArrayAdapter(Context context, int resource, int textViewResourceId, List<Contact> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.contact_row, parent, false);
        Contact contact = getItem(position);

        TextView name = (TextView) rowView.findViewById(R.id.name);
        TextView description = (TextView) rowView.findViewById(R.id.description);
        ImageView status = (ImageView) rowView.findViewById(R.id.status);

        name.setText(contact.getName());
        description.setText(contact.getStatusDescription());
        status.setImageResource(contact.getStatusResource());

        return rowView;
    }


}
