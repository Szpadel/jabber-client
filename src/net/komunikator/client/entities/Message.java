package net.komunikator.client.entities;

import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ziomek
 * Date: 31.05.13
 * Time: 02:26
 * To change this template use File | Settings | File Templates.
 */
public class Message {
    protected int id;
    protected Contact contact;
    protected String message;
    protected Date date;

    public Message(int id, Contact contact, String message, Date date) {
        this.id = id;
        this.contact = contact;
        this.message = message;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public Contact getContact() {
        return contact;
    }

    public String getMessage() {
        return message;
    }

    public Date getDate() {
        return date;
    }

    public String getHumanDate() {
        String out;

        Calendar cal = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        cal.setTime(date);
        today.setTime(new Date());

        if (cal.get(Calendar.YEAR) == today.get(Calendar.YEAR) &&
                cal.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)) {
            out = "Dzisiaj ";
        } else {
            out = cal.get(Calendar.DAY_OF_MONTH) + "-" +
                    cal.get(Calendar.MONTH) + " ";
        }

        out += cal.get(Calendar.HOUR_OF_DAY) + ":";
        int minutes = cal.get(Calendar.MINUTE);
        out += minutes < 10 ? "0" + minutes : minutes;

        return out;
    }
}
