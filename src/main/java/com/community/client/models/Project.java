package com.community.client.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id",updatable = false,nullable = false)
    private Long id;

    @Column(name = "project_name",nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "funds_required")
    private BigDecimal fundsRequired;

    @Column(name = "funds_received")
    private BigDecimal fundsReceived;

    @Column(name = "creator_userid",nullable = false)
    private Long creatorUserId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "community_id",nullable = false)
    private Community community;

    public Project() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getFundsRequired() {
        return fundsRequired;
    }

    public void setFundsRequired(BigDecimal fundsRequired) {
        this.fundsRequired = fundsRequired;
    }

    public BigDecimal getFundsReceived() {
        return fundsReceived;
    }

    public void setFundsReceived(BigDecimal fundsReceived) {
        this.fundsReceived = fundsReceived;
    }

    public Long getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(Long creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", fundsRequired=" + fundsRequired +
                ", fundsReceived=" + fundsReceived +
                ", creatorUserId=" + creatorUserId +
                ", community=" + community +
                '}';
    }
}
