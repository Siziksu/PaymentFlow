package com.siziksu.payment.data.persistence.model;

public final class Contact {

    public String name;
    public String phone;

    public Contact() {}

    @Override
    public String toString() {
        return "{\"Name\" : \"" + name + "\", \"Phone\" : \"" + phone + "\"}";
    }

    @Override
    public boolean equals(Object obj) {
        if (phone == null || !(obj instanceof Contact)) {
            return false;
        }
        return name.equals(((Contact) obj).name) && phone.equals(((Contact) obj).phone);
    }

    @Override
    public int hashCode() {
        return 31 * 17 + name.length() + phone.length();
    }
}
