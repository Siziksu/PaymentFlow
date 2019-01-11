package com.siziksu.payment.domain.main;

import com.siziksu.payment.common.function.Action;
import com.siziksu.payment.common.function.Consumer;
import com.siziksu.payment.domain.BaseDomainContract;
import com.siziksu.payment.domain.common.model.ContactDomain;

import java.util.List;

public interface MainDomainContract extends BaseDomainContract {

    void getContacts(Consumer<List<ContactDomain>> onContacts, Action orError);
}
