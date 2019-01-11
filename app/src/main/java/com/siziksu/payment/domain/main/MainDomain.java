package com.siziksu.payment.domain.main;

import com.siziksu.payment.App;
import com.siziksu.payment.common.function.Action;
import com.siziksu.payment.common.function.Consumer;
import com.siziksu.payment.common.utils.Print;
import com.siziksu.payment.data.RepositoryContract;
import com.siziksu.payment.data.model.CharacterData;
import com.siziksu.payment.data.model.ContactData;
import com.siziksu.payment.domain.common.manager.DisposablesManager;
import com.siziksu.payment.domain.common.model.ContactDomain;
import com.siziksu.payment.domain.common.utils.CollectionsUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public final class MainDomain implements MainDomainContract {

    @Inject
    RepositoryContract repository;
    @Inject
    DisposablesManager disposablesManager;

    public MainDomain() {
        App.get().getApplicationComponent().inject(this);
    }

    @Override
    public void register() {
        disposablesManager.setSize(1);
    }

    @Override
    public void unregister() {
        disposablesManager.dispose();
    }

    @Override
    public void getContacts(Consumer<List<ContactDomain>> onContacts, Action orError) {
        if (repository == null) { return; }
        Single<List<ContactData>> single1 = repository.getContacts();
        Single<List<CharacterData>> single2 = repository.getCharacters();
        disposablesManager.add(0, Single.zip(single1, single2, (contacts, characters) -> {
                                                 List<ContactDomain> list = getContactList(contacts, characters);
                                                 CollectionsUtils.sortUsersByName(list);
                                                 return list;
                                             }
        ).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        onContacts::accept,
                        throwable -> {
                            orError.execute();
                            Print.error("Error getting the contacts: " + throwable.getMessage(), throwable);
                        }
                ));
    }

    private List<ContactDomain> getContactList(List<ContactData> contacts, List<CharacterData> characters) {
        List<ContactDomain> list = new ArrayList<>();
        ContactDomain c;
        for (ContactData contact : contacts) {
            c = new ContactDomain();
            c.name = contact.name;
            c.phone = contact.phone;
            list.add(c);
        }
        for (CharacterData character : characters) {
            c = new ContactDomain();
            c.id = character.id;
            c.name = character.name;
            c.thumbnailPath = character.thumbnailPath;
            c.thumbnailExtension = character.thumbnailExtension;
            list.add(c);
        }
        return list;
    }
}
