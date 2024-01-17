package es.wacoco.nowle.controller;

import es.wacoco.nowle.Camel.Controller.CamelController;
import es.wacoco.nowle.Camel.Service.CamelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CamelControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CamelService camelService;

    @InjectMocks
    private CamelController camelController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(camelController).build();
    }

    @Test
    void testGetPatentService() throws Exception {
        when(camelService.patentServiceRoute()).thenReturn("Mocked result");

        mockMvc.perform(get("/api/v1/dev/patent"))
                .andExpect(status().isOk());
        // Add more assertions based on the expected behavior
    }

    @Test
    void testSearch() throws Exception {
        String searchTerm = "mockedTerm";
        when(camelService.searchRoute(searchTerm)).thenReturn("Mocked result");

        mockMvc.perform(get("/api/v1/dev/search").param("searchTerm", searchTerm))
                .andExpect(status().isOk());

    }

    @Test
    void testGetLinkedin() throws Exception {
        when(camelService.linkedinRoute()).thenReturn("Mocked result");

        mockMvc.perform(get("/api/v1/dev/linkedin"))
                .andExpect(status().isOk());

    }
}
