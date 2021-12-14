package com.community.client.repositories;
import com.community.client.models.Contact;
import com.community.client.models.DonationTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends CrudRepository<Contact,Long> {
    //This line of code below will save the user's input (example: queries and user details)
    Contact save(Contact contact);
}
