package com8.markmcilwrath.controller;

import com8.markmcilwrath.domain.Software;
import com8.markmcilwrath.domain.User;
import com8.markmcilwrath.service.SoftwareService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static com8.markmcilwrath.helper.toJson;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SoftwareControllerTest {

    private MockMvc mvc;

    @Mock
    private SoftwareService softwareService;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets/software");

    private static final String BASE_URL = "/software";

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        SoftwareController softwareController = new SoftwareController(softwareService);
        mvc = MockMvcBuilders
                .standaloneSetup(softwareController)
                .apply(documentationConfiguration(this.restDocumentation))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .alwaysDo(document(
                        "{method-name}/{step}/",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .build();
    }

    @Test
    public void addSoftwareTest() throws Exception {
        when(softwareService.getAllSoftwares()).thenReturn(Collections.emptySet());

        mvc.perform(
                post(BASE_URL + "/add/123")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(software())))
                .andExpect(status().isCreated())
                .andReturn();
    }

//    @Test
//    public void editSoftwareTest() throws Exception
//    {
//        when(softwareService.getAllSoftwares()).thenReturn(Collections.emptySet());
//
//        mvc.perform(
//                put(BASE_URL + "/update/ID")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(toJson(software())))
//                .andExpect(status().isOk())
//                .andReturn();
//    }

    @Test
    public void deleteSoftwareTest() throws Exception {
        when(softwareService.getAllSoftwares()).thenReturn(Collections.emptySet());

        mvc.perform(
                delete(BASE_URL + "/asd")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(software())))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    public void getAllSoftwareTest() throws Exception {
        when(softwareService.getAllSoftwares()).thenReturn(Collections.emptySet());

        mvc.perform(
                get(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(software())))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getSoftwareByIDTest() throws Exception {
        when(softwareService.getAllSoftwares()).thenReturn(Collections.emptySet());

        mvc.perform(
                get(BASE_URL + "/123")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(software())))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getSoftwareByVendorTest() throws Exception {
        when(softwareService.getAllSoftwares()).thenReturn(Collections.emptySet());

        mvc.perform(
                get(BASE_URL + "/vendor/1234")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(software())))
                .andExpect(status().isOk())
                .andReturn();
    }

    private Software software() {
        return new Software("name", "version");
    }
}
