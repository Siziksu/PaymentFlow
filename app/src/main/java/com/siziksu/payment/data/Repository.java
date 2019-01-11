package com.siziksu.payment.data;

import com.siziksu.payment.App;
import com.siziksu.payment.BuildConfig;
import com.siziksu.payment.data.client.MarvelClient;
import com.siziksu.payment.data.mapper.CharacterMapper;
import com.siziksu.payment.data.mapper.ContactMapper;
import com.siziksu.payment.data.model.CharacterData;
import com.siziksu.payment.data.model.ContactData;
import com.siziksu.payment.data.persistence.android.ContactClientContract;
import com.siziksu.payment.data.utils.Cryptography;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public final class Repository implements RepositoryContract {

    @Inject
    ContactClientContract contactClient;
    @Inject
    MarvelClient marvelClient;

    public Repository() {
        App.get().getApplicationComponent().inject(this);
    }

    @Override
    public Single<List<ContactData>> getContacts() {
        return contactClient.getContacts().map(contacts -> new ContactMapper().map(contacts));
    }

    @Override
    public Single<List<CharacterData>> getCharacters() {
        long ts = System.currentTimeMillis();
        String string = ts + BuildConfig.API_PRIVATE_KEY + BuildConfig.API_PUBLIC_KEY;
        String hash = Cryptography.md5(string);
        String apiKey = BuildConfig.API_PUBLIC_KEY;
        return marvelClient.getCharacters(ts, hash, apiKey, null, 0, 50).map(characters -> new CharacterMapper().map(characters.data.characters));
    }
}
