package com.growsari.application.server.dao.security;


import com.growsari.application.common.model.security.ActivityCategory;
import com.growsari.application.common.model.security.ActivityCategoryName;
import com.growsari.application.server.util.jpa.GrowsariJpaRepository;

public interface ActivityCategoryRepository extends GrowsariJpaRepository<ActivityCategory, ActivityCategoryName> {
}
