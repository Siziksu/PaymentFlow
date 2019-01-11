package com.siziksu.payment.domain.common.utils;

import com.siziksu.payment.domain.common.model.ContactDomain;

import java.util.Collections;
import java.util.List;

public final class CollectionsUtils {

    private CollectionsUtils() {}

    public static void sortUsersByName(List<ContactDomain> contacts) {
        Collections.sort(contacts, (contact1, contact2) -> contact1.name.compareToIgnoreCase(contact2.name));
    }
}
