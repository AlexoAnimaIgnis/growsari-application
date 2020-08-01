package com.growsari.application.server.controller.security;


import com.growsari.application.common.dto.security.AuthenticateResponseDTO;
import com.growsari.application.common.model.security.GrowsariUser;
import com.growsari.application.server.service.security.GrowsariUserDetails;
import com.growsari.application.server.service.security.UserService;
import com.growsari.application.server.util.security.ActivityGrantedAuthority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author alexander.ballester
 */
@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * Item 1: Register a User
     * @param growsariUser
     * @return
     */
    @PostMapping(value = {"/user/register"}, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    GrowsariUser registerUser(@RequestBody GrowsariUser growsariUser) {
        Assert.notNull(growsariUser, "User cannot be null");

        logger.debug(growsariUser.toString());

        return getUser(userService.saveUser(growsariUser).getId());
    }

    @GetMapping(value = {"/users/{id}"}, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    GrowsariUser getUser(@PathVariable String id) {
        GrowsariUser growsariUser = userService.getUser(id);
        return growsariUser;
    }

    /**
     * Item 2: User Login
     * @return
     */
    @GetMapping(value = {"/user/login"}, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    AuthenticateResponseDTO loginUser() {
        GrowsariUserDetails growsariUser = userService.authenticateUser();

        List<String> authorities = growsariUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        List<String> activities = growsariUser.getAuthorities().stream().filter(authority -> authority instanceof ActivityGrantedAuthority).
                map(authority -> ((ActivityGrantedAuthority) authority).getActivity().getName()).collect(Collectors.toList());
        return new AuthenticateResponseDTO(growsariUser.getUsername(), authorities, activities, growsariUser.getId());
    }
}
