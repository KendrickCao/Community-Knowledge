package com.community.client.controllers;

import com.community.client.models.DonationTransaction;
import com.community.client.services.DonationTransactionService;
import org.springframework.web.bind.annotation.*;

@RestController
public class DonationTransactionController {
    //DI for the service layer
    private final DonationTransactionService donationTransactionService;

    public DonationTransactionController(DonationTransactionService donationTransactionService) {
        this.donationTransactionService = donationTransactionService;
    }

    //API endpoints we define here
    //1. End point is to save the transaction
    @PostMapping("/api/new-transaction")
    public DonationTransaction saveNewTransaction(@RequestBody DonationTransaction donationTransaction){
        System.out.println("I am being hit");
        return donationTransactionService.saveTransaction(donationTransaction);
    }



}
