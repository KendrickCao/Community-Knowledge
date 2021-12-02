package com.community.client.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.jfr.DataAmount;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="transaction_table")
public class DonationTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long Id;

    @Column(name="donor_name", nullable = false)
    private String donorName;

    @Column(name="donor_email", nullable = false)
    private String donorEmail;

    @Column(name="amount", nullable = false, precision = 2)
    private float amount;

    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "project_id", nullable = false)
    private long projectId;

    @Column(name="card_cv", nullable = false)
    private String cardCv;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDonorEmail() {
        return donorEmail;
    }

    public void setDonorEmail(String donorEmail) {
        this.donorEmail = donorEmail;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public String getCardCv() {
        return cardCv;
    }

    public void setCardCv(String cardCv) {
        this.cardCv = cardCv;
    }
}
