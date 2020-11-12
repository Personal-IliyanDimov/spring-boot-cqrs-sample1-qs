package org.imd.cqrs.sample1.qs.cqrs.projectors;

import org.imd.cqrs.sample1.qs.cqrs.repository.UserReadRepository;
import org.imd.cqrs.sample1.qs.model.Address;
import org.imd.cqrs.sample1.qs.model.User;
import org.imd.cqrs.sample1.qs.model.UserAddress;

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
