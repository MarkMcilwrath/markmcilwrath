package com8.markmcilwrath.controller;

import com8.markmcilwrath.domain.LicenseArchive;
import com8.markmcilwrath.domain.LicenseAssignment;
import com8.markmcilwrath.service.LicenseArchiveService;
import com8.markmcilwrath.service.LicenseAssignmentService;
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

import static com8.markmcilwrath.helper.toJson;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LicenseArchiveControllerTest {

    private MockMvc mvc;

    @Mock
    private LicenseArchiveService licenseArchiveService;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets/license_archive");

    private static final String BASE_URL = "/license_archive";

    public void setup() {
        MockitoAnnotations.initMocks(this);
        LicenseArchiveController licenseArchiveController = new LicenseArchiveController(licenseArchiveService);
        mvc = MockMvcBuilders
                .standaloneSetup(licenseArchiveController)
                .apply(documentationConfiguration(this.restDocumentation))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .alwaysDo(document(
                        "{method-name}/{step}/",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .build();
    }

    @Test
//    public void getAllLicenseArchivesTest() throws Exception {
//        when(licenseArchiveService.getAllArchivedLicense()).thenReturn(Collections.emptySet());
//
//        mvc.perform(
//                get(BASE_URL)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(toJson(licenseArchive())))
//                .andExpect(status().isOk())
//                .andReturn();
//    }

    private LicenseArchive licenseArchive() {

        return new LicenseArchive ("licenseKey", LocalDate.EPOCH.minusMonths(1), LocalDate.EPOCH.plusMonths(1), Long.parseLong("12"));
    }
}
