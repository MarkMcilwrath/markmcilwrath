package com8.markmcilwrath.controller;//package com8.markmcilwrath.controller;
//
//import com8.markmcilwrath.domain.User;
//import com8.markmcilwrath.domain.Vendor;
//import com8.markmcilwrath.service.VendorService;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.JUnitRestDocumentation;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Collections;
//
//import static com8.markmcilwrath.helper.toJson;
//import static org.mockito.Mockito.when;
//import static org.mockito.ArgumentMatchers.any;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//public class VendorControllerTest {
//
//    private MockMvc mvc;
//
//    @Mock
//    private VendorService vendorService;
//
//    @Rule
//    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("target/generated-snippets/vendor");
//
//    private static final String BASE_URL = "/vendor";
//
//    @Before
//    public void setup()
//    {
//        MockitoAnnotations.initMocks(this);
//        VendorController vendorController = new VendorController(vendorService);
//        mvc = MockMvcBuilders
//                .standaloneSetup(vendorController)
//                .apply(documentationConfiguration(this.restDocumentation))
//                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
//                .alwaysDo(document(
//                        "{method-name}/{step}/",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint())))
//                .build();
//    }
//
//    @Test
//    public void addVendorTest() throws Exception {
//        when(vendorService.getAllVendors()).thenReturn(Collections.emptySet());
//
//        mvc.perform(
//                post(BASE_URL+ "/add")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(toJson(vendor())))
//                .andExpect(status().isCreated())
//                .andReturn();
//
//
//    }
//
//    @Test
//    public void getAllVendorsTets() throws Exception {
//        when(vendorService.getAllVendors()).thenReturn(Collections.emptySet());
//
//        mvc.perform(
//                get(BASE_URL)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(toJson(vendor())))
//                .andExpect(status().isOk())
//                .andReturn();
//    }
//    @Test
//    public void getVendorByID() throws Exception {
//        when(vendorService.getVendor(any())).thenReturn(vendor());
//
//        mvc.perform(
//                get(BASE_URL)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(toJson(vendor())))
//                .andExpect(status().isOk())
//                .andReturn();
//    }
//
//
//    private Vendor vendor() {
//        return new Vendor("name");
//    }
//
//
//}
