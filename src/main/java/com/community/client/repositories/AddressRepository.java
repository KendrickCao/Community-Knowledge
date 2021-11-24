package com.community.client.repositories;

import com.community.client.models.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address,Long> {

    //save the address
    Address save(Address address);
}
