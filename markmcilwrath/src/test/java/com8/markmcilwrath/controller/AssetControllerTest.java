package com8.markmcilwrath.controller;

import com8.markmcilwrath.domain.Asset;
import com8.markmcilwrath.service.AssetService;
import com8.markmcilwrath.service.UserService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class AssetControllerTest {

    private MockMvc mvc;

    @Mock
    private AssetService assetService;

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets/asset");

    private static final String BASE_URL = "/asset";

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        AssetController assetController = new AssetController(assetService);
        mvc = MockMvcBuilders
                .standaloneSetup(assetController)
                .apply(documentationConfiguration(this.restDocumentation))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .alwaysDo(document(
                        "{method-name}/{step}/",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint())))
                .build();

    }

    @Test
    public void getAssetbyAssetTagTest() throws Exception {
        when(assetService.getAssetbyAssetTag(any())).thenReturn(asset());

        mvc.perform(
                get(BASE_URL + "/1234")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(asset())))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAssetByHardwareTEST() throws Exception {
        when(assetService.getAllAssetForHardwareID(any())).thenReturn(Collections.emptySet());
        mvc.perform(
                get(BASE_URL + "/hardware/1234")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(asset())))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAllAssetsTest() throws Exception {
        when(assetService.getAllAsset()).thenReturn(Collections.emptySet());
        mvc.perform(
                get(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(asset())))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void getAllFreeAssetsTest() throws Exception {
        when(assetService.getAllFreeAsset()).thenReturn(Collections.emptySet());
        mvc.perform(
                get(BASE_URL + "/free")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(asset())))
                .andExpect(status().isOk())
                .andReturn();
    }

    public Asset asset(){
        return new Asset("12345", "67890", LocalDate.EPOCH.plusMonths(1));
    }
}
