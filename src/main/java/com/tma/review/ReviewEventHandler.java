package com.tma.review;

import com.tma.user.User;
import com.tma.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class ReviewEventHandler {
    private final UserRepository users;

    @Autowired
    public ReviewEventHandler(UserRepository users){
        this.users = users;
    }

    @HandleBeforeCreate
    public void addReviewerBasedOnLoggedInUser(Review review){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = users.findByUserName(username);
        review.setReviewer(user);
    }

}
