package com8.markmcilwrath.controller;

import com8.markmcilwrath.domain.User;
import com8.markmcilwrath.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static com8.markmcilwrath.helper.toJson;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserControllerTest {

    private MockMvc mvc;

    @Mock
    private UserService userService;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets/users");

    private static final String BASE_URL = "/user";


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        UserController userController = new UserController(userService);
        mvc = MockMvcBuilders
                .standaloneSetup(userController)
                .apply(documentationConfiguration(this.restDocumentation))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .alwaysDo(document(
                        "{method-name}/{step}/",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .build();

    }



    @Test
    public void addUsersTest() throws Exception {
        when(userService.getAllUsers()).thenReturn(Collections.emptySet());

        mvc.perform(
                post(BASE_URL + "/add")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        .content(toJson(user())))
                .andExpect(status().isCreated())
                .andReturn();
    }

    @Test
    public void updateUsersTest() throws Exception {
        when(userService.getAllUsers()).thenReturn(Collections.emptySet());

        mvc.perform(
                put(BASE_URL + "/update")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(user())))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deleteUsersTest() throws Exception {
        when(userService.getAllUsers()).thenReturn(Collections.emptySet());

        mvc.perform(
                delete(BASE_URL + "/asd")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(user())))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    public void getAllUsersTest() throws Exception {
        when(userService.getAllUsers()).thenReturn(Collections.emptySet());

        mvc.perform(
                get(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(user())))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getUserByIDTest() throws Exception {
        when(userService.getAllUsers()).thenReturn(Collections.emptySet());

        mvc.perform(
                get(BASE_URL + "/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(user())))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getUserIDByEmail() throws Exception {
        when(userService.getUserByEmail(any())).thenReturn(user());
        mvc.perform(
                get(BASE_URL + "/id/fristLast@test.com")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(user())))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getUserByEmailTest() throws Exception {
        when(userService.getAllUsers()).thenReturn(Collections.emptySet());

        mvc.perform(
                get(BASE_URL + "/fristLast@test.com")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(user())))
                .andExpect(status().isOk())
                .andReturn();
    }

    private User user() {
        return new User("firstName", "LastName", "fristLast@test.com", true);
    }


}
