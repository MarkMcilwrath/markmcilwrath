package com8.markmcilwrath.controller;

import com8.markmcilwrath.domain.License;
import com8.markmcilwrath.domain.User;
import com8.markmcilwrath.service.LicenseService;
import com8.markmcilwrath.service.SoftwareService;
import com8.markmcilwrath.service.UserService;
import org.hibernate.mapping.Set;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static com8.markmcilwrath.helper.toJson;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LicenseControllerTest {

    private MockMvc mvc;

    @Mock
    private LicenseService licenseService;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets/license");

    private static final String BASE_URL = "/license";

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        LicenseController licenseController = new LicenseController(licenseService);
        mvc = MockMvcBuilders
                .standaloneSetup(licenseController)
                .apply(documentationConfiguration(this.restDocumentation))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .alwaysDo(document(
                        "{method-name}/{step}/",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .build();

    }

//    @Test
//    public void addLicenseTest() throws Exception {
//        when(licenseService.getAllLicense()).thenReturn(Collections.emptySet());
//
//        mvc.perform(
//                post(BASE_URL + "/add/softwareId")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(toJson(license())))
//                .andExpect(status().isCreated())
//                .andReturn();
//    }

    @Test
    public void getLicenseByIDTest() throws Exception {
        when(licenseService.getLicense(any())).thenReturn(license());

        mvc.perform(
                get(BASE_URL + "/licenseKey")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(license())))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getLicenseBySoftwareTest() throws Exception {
        when(licenseService.getAllLicenseBySoftware(any())).thenReturn(Collections.emptySet());

        mvc.perform(
                get(BASE_URL + "/software/1234")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(license())))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAllLicensesTest() throws Exception {

        mvc.perform(
                get(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(license())))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAllFreeLicensesTest() throws Exception {

        mvc.perform(
                get(BASE_URL + "/free")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(license())))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAllExpiredLicensesTest() throws Exception {

        mvc.perform(
                get(BASE_URL + "/expired")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(license())))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void getAllLicensesWithExpiryDateTest() throws Exception {

        mvc.perform(
                get(BASE_URL + "/expiry")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(license())))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAllExpiringLicensesTest () throws Exception {

        mvc.perform(
                get(BASE_URL + "/expiring")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(license())))
                .andExpect(status().isOk())
                .andReturn();
    }

    private License license() {
        return new License("licenseKey", LocalDate.now(), LocalDate.EPOCH.plusMonths(1));
    }
}
