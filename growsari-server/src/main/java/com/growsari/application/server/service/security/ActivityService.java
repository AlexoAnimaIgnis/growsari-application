package com.growsari.application.server.service.security;

import com.growsari.application.common.model.security.GrowsariUser;
import com.growsari.application.common.model.security.UserActivity;
import com.growsari.application.common.model.security.UserActivityType;

import java.util.Set;

public interface ActivityService {

    Set<UserActivity> getActivities(GrowsariUser growsariUser);

    boolean hasUserActivity(GrowsariUser growsariUser, UserActivityType type);
}
