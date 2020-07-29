package com.growsari.application.server.controller.security;


import com.growsari.application.common.dto.security.AuthenticateResponseDTO;
import com.growsari.application.common.model.security.User;
import com.growsari.application.server.service.security.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.MediaType;

/**
 * @author alexander.ballester
 */
@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping(value = {"/register"}, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    User registerUser(@RequestBody User user) {
        Assert.notNull(user, "User cannot be null");

        logger.debug(user.toString());

        return getUser(userService.saveUser(user).getId());
    }

    @GetMapping(value = {"/users/{id}"}, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    User getUser(@PathVariable String id) {
        User user = userService.getUser(id);
        return user;
    }

    @PostMapping(value = {"/login"}, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    AuthenticateResponseDTO loginUser() {
        User user = userService.authenticateUser();
        return new AuthenticateResponseDTO(user.getId(), user.getName());
    }
}
