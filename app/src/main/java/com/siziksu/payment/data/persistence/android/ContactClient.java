package com.siziksu.payment.data.persistence.android;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.siziksu.payment.App;
import com.siziksu.payment.data.persistence.model.Contact;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public final class ContactClient implements ContactClientContract {

    @Inject
    ContentResolver resolver;

    public ContactClient() {
        App.get().getApplicationComponent().inject(this);
    }

    @Override
    public Single<List<Contact>> getContacts() {
        return Single.create(emitter -> {
            List<Contact> contacts = null;
            String selection = ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " != " + "\'\' AND " + ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " NOT NULL";
            String orderBy = "display_name ASC";
            Cursor cursor = resolver.query(ContactsContract.Contacts.CONTENT_URI, null, selection, null, orderBy);
            Cursor cursorPhone;
            if (cursor != null && cursor.getCount() > 0) {
                contacts = new ArrayList<>();
                Contact item;
                while (cursor.moveToNext()) {
                    String id;
                    String name;
                    String phone = "";
                    id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY));
                    if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                        cursorPhone = resolver.query(
                                ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                                , null
                                , ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?"
                                , new String[]{id}
                                , null);
                        if (cursorPhone != null && cursorPhone.getCount() > 0) {
                            while (cursorPhone.moveToNext()) {
                                phone = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            }
                            cursorPhone.close();
                        }
                    }
                    item = new Contact();
                    item.name = name;
                    item.phone = phone;
                    contacts.add(item);
                }
                cursor.close();
            }
            if (contacts != null) {
                emitter.onSuccess(contacts);
            }
            if (!emitter.isDisposed()) {
                emitter.onError(new Exception("No elements found"));
            }
        });
    }
}
