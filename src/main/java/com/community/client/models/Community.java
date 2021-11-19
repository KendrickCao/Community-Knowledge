package com.community.client.models;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "community_table")
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_id")
    private Long id;

    @Column(name = "community_name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "userObjectSet", cascade = CascadeType.ALL)
    private Set<Community> communitySet;


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

    public Set<Community> getCommunitySet() {
        return communitySet;
    }

    public void setCommunitySet(Set<Community> communitySet) {
        this.communitySet = communitySet;
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
                ", communitySet=" + communitySet +
                '}';
    }
}
