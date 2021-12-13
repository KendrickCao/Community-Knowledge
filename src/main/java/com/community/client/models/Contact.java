package com.community.client.models;
import javax.persistence.*;

@Entity
@Table(name = "contact_table")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contactSubmission_id")
    private Long id;

}
