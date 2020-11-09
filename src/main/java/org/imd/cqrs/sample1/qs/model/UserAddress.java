package org.imd.cqrs.sample1.qs.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
public class UserAddress {

    private Map<String, Set<Address>> addressByRegion = new HashMap<>();

}
