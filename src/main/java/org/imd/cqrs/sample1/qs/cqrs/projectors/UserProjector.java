package org.imd.cqrs.sample1.qs.cqrs.projectors;

import org.imd.cqrs.sample1.qs.cqrs.repository.UserReadRepository;
import org.imd.cqrs.sample1.qs.model.Address;
import org.imd.cqrs.sample1.qs.model.Contact;
import org.imd.cqrs.sample1.qs.model.User;
import org.imd.cqrs.sample1.qs.model.UserAddress;
import org.imd.cqrs.sample1.qs.model.UserContact;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class UserProjector {

    UserReadRepository readRepository = new UserReadRepository();

    public UserProjector(UserReadRepository readRepository) {
        this.readRepository = readRepository;
    }

    public void project(User user) {
        UserContact userContact = Optional.ofNullable(readRepository.getUserContact(user.getUserid()))
            .orElse(new UserContact());
        Map<String, Set<Contact>> contactByType = new HashMap<>();
        for (Contact contact : user.getContacts()) {
            Set<Contact> contacts = Optional.ofNullable(contactByType.get(contact.getType()))
                .orElse(new HashSet<>());
            contacts.add(contact);
            contactByType.put(contact.getType(), contacts);
        }
        userContact.setContactByType(contactByType);
        readRepository.addUserContact(user.getUserid(), userContact);

        UserAddress userAddress = Optional.ofNullable(readRepository.getUserAddress(user.getUserid()))
            .orElse(new UserAddress());
        Map<String, Set<Address>> addressByRegion = new HashMap<>();
        for (Address address : user.getAddresses()) {
            Set<Address> addresses = Optional.ofNullable(addressByRegion.get(address.getState()))
                .orElse(new HashSet<>());
            addresses.add(address);
            addressByRegion.put(address.getState(), addresses);
        }
        userAddress.setAddressByRegion(addressByRegion);
        readRepository.addUserAddress(user.getUserid(), userAddress);
    }
}
