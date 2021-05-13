package com8.markmcilwrath.controller;

import com8.markmcilwrath.domain.Hardware;
import com8.markmcilwrath.service.HardwareService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HardwareControllerTest {

    private MockMvc mvc;

    @Mock
    private HardwareService hardwareService;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets/hardware");

    private static final String BASE_URL = "/hardware";

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        HardwareController hardwareController = new HardwareController(hardwareService);
        mvc = MockMvcBuilders
                .standaloneSetup(hardwareController)
                .apply(documentationConfiguration(this.restDocumentation))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .alwaysDo(document(
                        "{method-name}/{step}/",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .build();
    }

    @Test
    public void getAllSoftwareTest() throws Exception {
        when(hardwareService.getAllHardware()).thenReturn(Collections.emptySet());

        mvc.perform(
                get(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(hardware())))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getHardwareByID() throws Exception {

        mvc.perform(
                get(BASE_URL + "/123")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(hardware())))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getSoftwareByVendorTest() throws Exception {
        when(hardwareService.getAllHardwareByVendor(any())).thenReturn(Collections.emptySet());

        mvc.perform(
                get(BASE_URL + "/vendor/1234")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(hardware())))
                .andExpect(status().isOk())
                .andReturn();
    }



    public Hardware hardware ()
    {
        return new Hardware("1234", "name", "model");
    }

}
