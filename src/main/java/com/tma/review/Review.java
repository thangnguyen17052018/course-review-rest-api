package com.tma.review;

import com.tma.core.BaseEntity;
import com.tma.course.Course;
import com.tma.user.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class Review extends BaseEntity {
    private int rating;
    private String description;
    @ManyToOne
    private Course course;
    @ManyToOne
    private User reviewer;

    // TODO: csd - We are duplicating code here for every single entity. Share it?
    protected Review() {
        super();
    }

    public Review(int rating, String description) {
        this();
        this.rating = rating;
        this.description = description;
    }

    public User getReviewer() {
        return reviewer;
    }

    public void setReviewer(User reviewer) {
        this.reviewer = reviewer;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
