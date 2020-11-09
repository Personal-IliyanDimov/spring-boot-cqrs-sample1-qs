package org.imd.cqrs.sample1.qs.cqrs.repository;

import org.imd.cqrs.sample1.qs.model.UserAddress;
import org.imd.cqrs.sample1.qs.model.UserContact;

import java.util.HashMap;
import java.util.Map;

public class UserReadRepository {

    private Map<String, UserAddress> userAddress = new HashMap<>();

    private Map<String, UserContact> userContact = new HashMap<>();

    public void addUserAddress(String id, UserAddress user) {
        userAddress.put(id, user);
    }

    public UserAddress getUserAddress(String id) {
        return userAddress.get(id);
    }

    public void addUserContact(String id, UserContact user) {
        userContact.put(id, user);
    }

    public UserContact getUserContact(String id) {
        return userContact.get(id);
    }

}