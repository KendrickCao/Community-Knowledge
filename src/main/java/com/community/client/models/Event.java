package com.community.client.models;

import javax.persistence.*;

@Entity
@Table(name = "event_table")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", updatable = false, nullable = false)
    private Long id;

    @Column (name = "event_date", nullable = false)
    private String date;

    @Column (name = "event_name", nullable = false)
    private String name;

    @Column (name = "about_section", nullable = false)
    private String aboutSection;

    @Column (name = "event_contributors", nullable = false)
    private String contributors;

    @Column(name = "creator_userid",nullable = false)
    private Long creatorUserId;

    public Event(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAboutSection() {
        return aboutSection;
    }

    public void setAboutSection(String aboutSection) {
        this.aboutSection = aboutSection;
    }

    public String getContributors() {
        return contributors;
    }

    public void setContributors(String contributors) {
        this.contributors = contributors;
    }

    public Long getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(Long creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", name='" + name + '\'' +
                ", aboutSection='" + aboutSection + '\'' +
                ", contributors='" + contributors + '\'' +
                ", creatorUserId=" + creatorUserId +
                '}';
    }
}
