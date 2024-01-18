package es.wacoco.nowle.service;

import es.wacoco.nowle.Camel.Service.CamelService;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class CamelServiceTest {

    @Mock
    private CamelContext camelContext;

    @Mock
    private ProducerTemplate producerTemplate;

    @InjectMocks
    private CamelService camelService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPatentServiceRoute() {
        // Arrange
        when(camelContext.createProducerTemplate()).thenReturn(producerTemplate);
        when(producerTemplate.requestBody("direct:patent", null, String.class)).thenReturn("Mocked Patent Response");

        // Act
        String result = camelService.patentServiceRoute();

        // Assert

    }

    @Test
    void testLinkedinRoute() {
        // Arrange
        when(camelContext.createProducerTemplate()).thenReturn(producerTemplate);
        when(producerTemplate.requestBody("direct:linkedin", null, String.class)).thenReturn("Mocked LinkedIn Response");

        // Act
        String result = camelService.linkedinRoute();

        // Assert

    }

    @Test
    void testSearchRoute() {
        // Arrange
        when(camelContext.createProducerTemplate()).thenReturn(producerTemplate);
        when(producerTemplate.requestBody("direct:patent", null, String.class)).thenReturn("Mocked Patent Response");
        when(producerTemplate.requestBody("direct:linkedin", null, String.class)).thenReturn("Mocked LinkedIn Response");

        // Act
        String result = camelService.searchRoute("searchTerm");

        // Assert

    }
}

