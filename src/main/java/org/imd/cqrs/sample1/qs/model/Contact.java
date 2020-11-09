package org.imd.cqrs.sample1.qs.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contact {

    private String type;
    private String detail;

}
