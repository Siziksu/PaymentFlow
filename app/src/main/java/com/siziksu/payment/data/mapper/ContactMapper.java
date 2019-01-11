package com.siziksu.payment.data.mapper;

import com.siziksu.payment.common.mapper.Mapper;
import com.siziksu.payment.data.model.ContactData;
import com.siziksu.payment.data.persistence.model.Contact;

public final class ContactMapper extends Mapper<Contact, ContactData> {

    @Override
    public ContactData map(Contact unmapped) {
        ContactData mapped = new ContactData();
        mapped.name = unmapped.name;
        mapped.phone = unmapped.phone;
        return mapped;
    }

    @Override
    public Contact unMap(ContactData mapped) {
        Contact unmapped = new Contact();
        unmapped.name = mapped.name;
        unmapped.phone = mapped.phone;
        return unmapped;
    }
}
