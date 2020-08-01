package com.growsari.application.server.controller.security;

import com.growsari.application.common.dto.security.AuthenticateResponseDTO;
import com.growsari.application.common.model.security.GrowsariUser;
import com.growsari.application.server.service.security.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class GrowsariUserControllerTest {
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
//        GrowsariUser growsariUser = new GrowsariUser(EMAIL_PROP, NAME_PROP, PASSWORD_PROP);
//        growsariUser.setId(ID_PROP);
//
//        Mockito.when(userService.authenticateUser()).thenReturn(growsariUser);
//
//        AuthenticateResponseDTO result = userController.loginUser();
//
//        Assert.assertEquals(growsariUser.getName(), result.getName());
//        Assert.assertEquals(growsariUser.getId(), result.getId());
    }

    @Test
    public void registerUser(){
        GrowsariUser growsariUser = new GrowsariUser(EMAIL_PROP, NAME_PROP, PASSWORD_PROP);
        growsariUser.setId(ID_PROP);

        Mockito.when(userService.getUser(ID_PROP)).thenReturn(growsariUser);
        Mockito.when(userService.saveUser(growsariUser)).thenReturn(growsariUser);

        GrowsariUser result = userController.registerUser(growsariUser);

        Assert.assertEquals(growsariUser, result);
    }

    @Test
    public void getUser(){
        GrowsariUser growsariUser = new GrowsariUser(EMAIL_PROP, NAME_PROP, PASSWORD_PROP);
        growsariUser.setId(ID_PROP);

        Mockito.when(userService.getUser(ID_PROP)).thenReturn(growsariUser);

        GrowsariUser result = userController.getUser(ID_PROP);

        Assert.assertEquals(growsariUser, result);

    }
}
