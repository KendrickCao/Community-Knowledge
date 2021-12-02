package com.community.client.repositories;
import com.community.client.models.DonationTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationTransactionRepository extends CrudRepository<DonationTransaction,Long>{
    //1) Save the Donation Transaction details
    DonationTransaction save(DonationTransaction donationTransaction);

}
