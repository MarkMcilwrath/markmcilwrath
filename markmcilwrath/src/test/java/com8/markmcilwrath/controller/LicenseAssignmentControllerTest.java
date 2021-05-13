package com8.markmcilwrath.controller;

import com8.markmcilwrath.domain.License;
import com8.markmcilwrath.domain.LicenseAssignment;
import com8.markmcilwrath.service.LicenseArchiveService;
import com8.markmcilwrath.service.LicenseAssignmentService;
import com8.markmcilwrath.service.LicenseService;
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

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static com8.markmcilwrath.helper.toJson;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LicenseAssignmentControllerTest {

    private MockMvc mvc;

    @Mock
    private LicenseAssignmentService licenseAssignmentService;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets/license_assign");

    private static final String BASE_URL = "/license_assign";

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        LicenseAssignmentController licenseAssignmentController = new LicenseAssignmentController(licenseAssignmentService);
        mvc = MockMvcBuilders
                .standaloneSetup(licenseAssignmentController)
                .apply(documentationConfiguration(this.restDocumentation))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .alwaysDo(document(
                        "{method-name}/{step}/",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .build();

    }

    @Test
    public void getAllLicenseAssignmentsTest() throws Exception {
        when(licenseAssignmentService.getAllAssignments()).thenReturn(Collections.emptySet());

        mvc.perform(
                get(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(licenseAssignment())))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAssignmentByIDTest() throws Exception {
        when(licenseAssignmentService.getAssignmentByUUID(any())).thenReturn(licenseAssignment());
        mvc.perform(
                get(BASE_URL + "/assignmentID" )
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(licenseAssignment())))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAssignmentByIDAsIterableTest() throws Exception {
        when(licenseAssignmentService.getAssignmentAsIterable(any())).thenReturn(Collections.emptySet());
        mvc.perform(
                get(BASE_URL + "/assignmentID" )
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(licenseAssignment())))
                .andExpect(status().isOk())
                .andReturn();
    }


    private LicenseAssignment licenseAssignment() {
        return new LicenseAssignment("licenseKey","userID", LocalDate.now(), true);
    }

}
