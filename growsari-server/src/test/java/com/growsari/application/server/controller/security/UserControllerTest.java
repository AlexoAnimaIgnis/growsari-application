package com.growsari.application.server.controller.security;

import com.growsari.application.common.dto.security.AuthenticateResponseDTO;
import com.growsari.application.common.model.security.User;
import com.growsari.application.server.service.security.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class UserControllerTest {
    private static final String ID_PROP = "PROP1";
    private static final String NAME_PROP = "NAME1";
    private static final String EMAIL_PROP = "EMAIL1";
    private static final String PASSWORD_PROP = "PASSWORD1";

    @Mock
    private UserService userService;
    @InjectMocks
    private UserController userController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loginUser(){
        User user = new User(EMAIL_PROP, NAME_PROP, PASSWORD_PROP);
        user.setId(ID_PROP);

        Mockito.when(userService.authenticateUser()).thenReturn(user);

        AuthenticateResponseDTO result = userController.loginUser();

        Assert.assertEquals(user.getName(), result.getName());
        Assert.assertEquals(user.getId(), result.getId());
    }

    @Test
    public void registerUser(){
        User user = new User(EMAIL_PROP, NAME_PROP, PASSWORD_PROP);
        user.setId(ID_PROP);

        Mockito.when(userService.getUser(ID_PROP)).thenReturn(user);
        Mockito.when(userService.saveUser(user)).thenReturn(user);

        User result = userController.registerUser(user);

        Assert.assertEquals(user, result);
    }

    @Test
    public void getUser(){
        User user = new User(EMAIL_PROP, NAME_PROP, PASSWORD_PROP);
        user.setId(ID_PROP);

        Mockito.when(userService.getUser(ID_PROP)).thenReturn(user);

        User result = userController.getUser(ID_PROP);

        Assert.assertEquals(user, result);

    }
}
