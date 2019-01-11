package com.siziksu.payment.data.persistence.android;

import com.siziksu.payment.data.persistence.model.Contact;

import java.util.List;

import io.reactivex.Single;

public interface ContactClientContract {

    Single<List<Contact>> getContacts();
}
