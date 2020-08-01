package com.growsari.application.server.controller.security;


import com.growsari.application.common.dto.security.AuthenticateResponseDTO;
import com.growsari.application.common.model.security.GrowsariUser;
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

    @GetMapping(value = {"/login"}, produces = MediaType.APPLICATION_JSON)
    @ResponseBody
    AuthenticateResponseDTO loginUser() {
        GrowsariUser growsariUser = userService.authenticateUser();
        return new AuthenticateResponseDTO(growsariUser.getId(), growsariUser.getName());
    }
}
