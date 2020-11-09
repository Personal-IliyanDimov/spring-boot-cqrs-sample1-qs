package org.imd.cqrs.sample1.qs.cqrs.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.imd.cqrs.sample1.qs.model.Address;
import org.imd.cqrs.sample1.qs.model.Contact;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class UpdateUserCommand {

    private String userId;
    private Set<Address> addresses = new HashSet<>();
    private Set<Contact> contacts = new HashSet<>();

}
