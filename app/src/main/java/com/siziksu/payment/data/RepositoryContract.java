package com.siziksu.payment.data;

import com.siziksu.payment.data.model.CharacterData;
import com.siziksu.payment.data.model.ContactData;

import java.util.List;

import io.reactivex.Single;

public interface RepositoryContract {

    Single<List<ContactData>> getContacts();

    Single<List<CharacterData>> getCharacters();
}
