package com.growsari.application.server.controller.security;

import com.growsari.application.common.dto.security.AuthenticateResponseDTO;
import com.growsari.application.common.model.security.GrowsariUser;
import com.growsari.application.common.model.security.UserActivity;
import com.growsari.application.common.model.security.UserActivityType;
import com.growsari.application.server.service.security.GrowsariUserDetails;
import com.growsari.application.server.service.security.UserService;
import com.growsari.application.server.util.security.ActivityGrantedAuthority;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        List<GrantedAuthority> authorities = Stream.of(
                new ActivityGrantedAuthority(new UserActivity(
                        new GrowsariUser(), UserActivityType.CREATE_MESSAGE
                )),
                new ActivityGrantedAuthority( new UserActivity(
                        new GrowsariUser(), UserActivityType.CREATE_TOPIC
                ))
        ).collect(Collectors.toList());

        GrowsariUserDetails growsariUserDetails =
                new GrowsariUserDetails(NAME_PROP,
                        PASSWORD_PROP,true,
                        true, true, true, authorities);
        growsariUserDetails.setId(ID_PROP);

        Mockito.when(userService.authenticateUser()).thenReturn(growsariUserDetails);

        AuthenticateResponseDTO responseDTO = userController.loginUser();

        Assert.assertEquals(responseDTO.getId(), ID_PROP);
        Assert.assertEquals(responseDTO.getName(), NAME_PROP);
        Assert.assertEquals(2, responseDTO.getActivities().size());

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
