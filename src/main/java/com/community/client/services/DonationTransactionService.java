package com.community.client.services;

import com.community.client.models.DonationTransaction;
import com.community.client.repositories.DonationTransactionRepository;
import org.springframework.stereotype.Service;


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
}
