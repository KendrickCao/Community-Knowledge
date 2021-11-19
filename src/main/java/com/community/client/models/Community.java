package com.community.client.models;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "community_table")
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id",updatable = false)
    private Long id;

    @Column(name = "community_name")
    private String name;

    @Column(name = "description")
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "communitySet", fetch = FetchType.EAGER)
    private Set<UserObject> userObjectSet = new HashSet<>();

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

    public Set<UserObject> getUserObjectSet() {
        return userObjectSet;
    }

    public void setUserObjectSet(Set<UserObject> userObjectSet) {
        this.userObjectSet = userObjectSet;
    }

    //No Args Constructor
    public Community() {
    }

    @Override
    public String toString() {
        return "Community{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", userObjectSet=" + userObjectSet +
                '}';
    }

    public void addUserToCommunity(UserObject userObject) {
        userObjectSet.add(userObject);
    }
}
