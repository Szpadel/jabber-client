package net.komunikator.client.UI.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import net.komunikator.client.R;
import net.komunikator.client.entities.Message;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 31.05.13
 * Time: 04:13
 * To change this template use File | Settings | File Templates.
 */
public class MessagesArrayAdapter extends ArrayAdapter<Message> {
    public MessagesArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public MessagesArrayAdapter(Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public MessagesArrayAdapter(Context context, int textViewResourceId, Message[] objects) {
        super(context, textViewResourceId, objects);
    }

    public MessagesArrayAdapter(Context context, int resource, int textViewResourceId, Message[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public MessagesArrayAdapter(Context context, int textViewResourceId, List<Message> objects) {
        super(context, textViewResourceId, objects);
    }

    public MessagesArrayAdapter(Context context, int resource, int textViewResourceId, List<Message> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Message message = getItem(position);
        View rowView;

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(message.getContact() == null) {
            rowView = inflater.inflate(R.layout.messages_my_message, parent, false);
        }else {
            rowView = inflater.inflate(R.layout.messages_contact_message, parent, false);
            ImageView status = (ImageView) rowView.findViewById(R.id.status);
            status.setImageResource(message.getContact().getStatusResource());
        }

        TextView mess = (TextView) rowView.findViewById(R.id.conversation_message);
        TextView date = (TextView) rowView.findViewById(R.id.conversation_date);

        mess.setText(message.getMessage());
        date.setText(message.getHumanDate());

        return rowView;
    }
}
