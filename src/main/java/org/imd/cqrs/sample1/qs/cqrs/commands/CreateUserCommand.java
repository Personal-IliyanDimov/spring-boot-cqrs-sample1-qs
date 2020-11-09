package org.imd.cqrs.sample1.qs.cqrs.commands;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUserCommand {

    private String userId;
    private String firstName;
    private String lastName;

}
