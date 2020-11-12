package org.imd.cqrs.sample1.qs.es.service;




import org.imd.cqrs.sample1.qs.es.repository.EventStore;
import org.imd.cqrs.sample1.qs.model.Address;
import org.imd.cqrs.sample1.qs.model.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserService {

    private EventStore repository;

    public UserService(EventStore repository) {
        this.repository = repository;
    }

    public Set<Contact> getContactByType(String userId, String contactType) throws Exception {
        User user = UserUtility.recreateUserState(repository, userId);
        if (user == null)
            throw new Exception("User does not exist.");
        return user.getContacts()
            .stream()
            .filter(c -> c.getType()
                .equals(contactType))
            .collect(Collectors.toSet());
    }

    public Set<Address> getAddressByRegion(String userId, String state) throws Exception {
        User user = UserUtility.recreateUserState(repository, userId);
        if (user == null)
            throw new Exception("User does not exist.");
        return user.getAddresses()
            .stream()
            .filter(a -> a.getState()
                .equals(state))
            .collect(Collectors.toSet());
    }
}
