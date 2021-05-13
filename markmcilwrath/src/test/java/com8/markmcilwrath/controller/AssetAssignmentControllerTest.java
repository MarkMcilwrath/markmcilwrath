package com8.markmcilwrath.controller;

import com8.markmcilwrath.domain.AssetAssignment;
import com8.markmcilwrath.service.AssetAssignmentService;
import com8.markmcilwrath.service.AssetService;
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

import static com8.markmcilwrath.helper.toJson;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AssetAssignmentControllerTest {
    private MockMvc mvc;

    @Mock
    private AssetAssignmentService assetAssignmentService;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets/asset_assign");

    private static final String BASE_URL = "/asset_assign";

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AssetAssignmentController assetAssignmentController = new AssetAssignmentController(assetAssignmentService);
        mvc = MockMvcBuilders
                .standaloneSetup(assetAssignmentController)
                .apply(documentationConfiguration(this.restDocumentation))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .alwaysDo(document(
                        "{method-name}/{step}/",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .build();

    }

    @Test
    public void getAssignentByAssignmentID() throws Exception {
        when(assetAssignmentService.getAssignmentByUUID(any())).thenReturn(assetAssignment());

        mvc.perform(
                get(BASE_URL + "/1234")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(assetAssignment())))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAssignmentByAssignmentIDasIterable() throws Exception {
        when(assetAssignmentService.getAssignmentAsIterable(any())).thenReturn(Collections.emptySet());

        mvc.perform(
                get(BASE_URL + "/1234")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(assetAssignment())))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAssignmentsByUserTest() throws Exception {
        when(assetAssignmentService.getAssignmentAsIterable(any())).thenReturn(Collections.emptySet());

        mvc.perform(
                get(BASE_URL + "/user/1234")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(assetAssignment())))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void getAllAssignmentsNotApprovedTest() throws Exception {
        when(assetAssignmentService.getAllAssignmentsNotApproved()).thenReturn(Collections.emptySet());

        mvc.perform(
                get(BASE_URL + "/approve")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(assetAssignment())))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAllAssignmentTags() throws Exception {
        when(assetAssignmentService.getTags(any())).thenReturn(Collections.emptySet());

        mvc.perform(
                get(BASE_URL + "/1234")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(assetAssignment())))
                .andExpect(status().isOk())
                .andReturn();
    }

    public AssetAssignment assetAssignment()
    {
        return new AssetAssignment("assetTag", "UserID", LocalDate.now());
    }

}
