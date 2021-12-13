package com.community.client.services;

import com.community.client.models.DonationTransaction;
import com.community.client.repositories.DonationTransactionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


@Service
public class DonationTransactionService {
    //DI the repository
    private final DonationTransactionRepository donationTransactionRepository;

    public DonationTransactionService(DonationTransactionRepository donationTransactionRepository) {
        this.donationTransactionRepository = donationTransactionRepository;
    }

    //This is the actual method which we will use in the controller
    public DonationTransaction saveTransaction(DonationTransaction donationTransaction){
        DonationTransaction savedTransaction = donationTransactionRepository.save(donationTransaction);
        return savedTransaction;

    }
    // This will enable the user to identify if the user is present
    public Set<DonationTransaction> getProjectById(String id){
//        Optional<DonationTransaction> donationTransactionOptional = Optional.ofNullable(donationTransactionRepository.getDonationTransactionByProjectId(id));
//        if (donationTransactionOptional.isPresent()){
//            return donationTransactionOptional.get();
//        }else {
//            return null;
//        }

    return donationTransactionRepository.getDonationTransactionByProjectId(id);
    }

}
