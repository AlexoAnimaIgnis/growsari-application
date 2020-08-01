package com.growsari.application.server.service.security;

import com.growsari.application.common.model.security.GrowsariUser;
import com.growsari.application.common.model.security.UserActivity;
import com.growsari.application.common.model.security.UserActivityType;
import com.growsari.application.server.dao.security.ActivityCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service("activityService")
@Transactional(propagation = Propagation.REQUIRED)
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private GrowsariUserDetailsHolder growsariUserDetailsHolder;
    @Autowired
    private ActivityCategoryRepository activityCategoryRepository;


    @Override
    public Set<UserActivity> getActivities(GrowsariUser growsariUser) {
        Set<UserActivity> userActivities = new HashSet<>();
        userActivities.addAll(growsariUser.getActivitySet());
        return userActivities;
    }

    @Override
    public boolean hasUserActivity(GrowsariUser growsariUser, UserActivityType type) {
        return false;
    }
}
