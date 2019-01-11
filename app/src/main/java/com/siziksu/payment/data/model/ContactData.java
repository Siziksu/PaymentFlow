package com.siziksu.payment.data.model;

public final class ContactData {

    public String name;
    public String phone;

    public ContactData() {}

    @Override
    public String toString() {
        return "{\"Name\" : \"" + name + "\", \"Phone\" : \"" + phone + "\"}";
    }

    @Override
    public boolean equals(Object obj) {
        if (phone == null || !(obj instanceof ContactData)) {
            return false;
        }
        return name.equals(((ContactData) obj).name) && phone.equals(((ContactData) obj).phone);
    }

    @Override
    public int hashCode() {
        return 31 * 17 + name.length() + phone.length();
    }
}
