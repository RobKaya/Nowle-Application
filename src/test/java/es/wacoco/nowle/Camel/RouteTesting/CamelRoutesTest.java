package es.wacoco.nowle.Camel.RouteTesting;

import es.wacoco.nowle.Camel.Routes.CamelRoutes;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;


public class CamelRoutesTest extends CamelTestSupport {

    @Override
    protected RoutesBuilder createRouteBuilder() {
        return new CamelRoutes();
    }

    @Test
    public void testSearchRoute() throws Exception {
        // Mock the patent and LinkedIn endpoints
        AdviceWith.adviceWith(context.getRouteDefinition("patent"), context
                , new AdviceWithRouteBuilder() {
                    @Override
                    public void configure() throws Exception {
                        replaceFromWith("direct:mockPatent");
                        mockEndpointsAndSkip("http://localhost:3000/patentservice");
                    }
                });


        AdviceWith.adviceWith(context.getRouteDefinition("linkedin"), context, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {
                replaceFromWith("direct:mockLinkedin");
                mockEndpointsAndSkip("http://localhost:3000/linkedin");
            }
        });

        // Define responses for the mocked endpoints
        getMockEndpoint("mock:direct:mockPatent").whenAnyExchangeReceived(exchange -> {
            exchange.getIn().setBody("tttt");
        });

        getMockEndpoint("mock:direct:mockLinkedin").whenAnyExchangeReceived(exchange -> {
            exchange.getIn().setBody("tttt");
        });

        // Send a test message
        template.sendBodyAndHeader("direct:searchRoute", "tttt", "searchTerm", "Test Search Term");

        // Perform assertions
        getMockEndpoint("mock:direct:mockPatent").assertIsSatisfied();
        getMockEndpoint("mock:direct:mockLinkedin").assertIsSatisfied();

        // You can also add more assertions here to validate the output of the route
    }
}