package com.siziksu.payment.presenter.mapper;

import com.siziksu.payment.common.mapper.Mapper;
import com.siziksu.payment.domain.common.model.ContactDomain;
import com.siziksu.payment.ui.common.model.Contact;

public final class ContactMapper extends Mapper<ContactDomain, Contact> {

    @Override
    public Contact map(ContactDomain unmapped) {
        Contact mapped = new Contact();
        mapped.id = unmapped.id;
        mapped.name = unmapped.name;
        mapped.phone = unmapped.phone;
        mapped.thumbnailPath = unmapped.thumbnailPath;
        mapped.thumbnailExtension = unmapped.thumbnailExtension;
        return mapped;
    }

    @Override
    public ContactDomain unMap(Contact mapped) {
        ContactDomain unmapped = new ContactDomain();
        unmapped.id = mapped.id;
        unmapped.name = mapped.name;
        unmapped.phone = mapped.phone;
        unmapped.thumbnailPath = mapped.thumbnailPath;
        unmapped.thumbnailExtension = mapped.thumbnailExtension;
        return unmapped;
    }
}
