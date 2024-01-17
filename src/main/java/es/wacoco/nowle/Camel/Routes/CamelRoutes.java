package es.wacoco.nowle.Camel.Routes;

import com.jayway.jsonpath.JsonPath;
import org.apache.camel.AggregationStrategy;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CamelRoutes extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        /*
        * this should go through both camel patent and linkden and take the user search input like title
        * after that it search title in the patent and when it finds it, it returns the json block and also take the
        * inventor name and company name and look for those and sees if there is a match  in linkedin
        * after it finds in linkedin, the result wiill be json from patent and json block for linkedin.
        * */
        from("direct:searchRoute")
                .multicast()  // Multicast to run both routes in parallel
                .parallelProcessing()
                .to("direct:patent", "direct:linkedin")
                .end()
                .log("Search results: ${body}")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        // Process the search results and perform matching logic


                        // Assuming the search term is passed as a header
                        String searchTerm = exchange.getIn().getHeader("searchTerm", String.class);

                        // Assuming the search results are List<Map<String, String>>
                        List<Map<String, String>> patentResults = exchange.getIn().getBody(List.class);
                        List<Map<String, String>> linkedinResults = exchange.getOut().getBody(List.class);

                        // Filter patent results based on the search term
                        List<Map<String, String>> matchingPatents = new ArrayList<>();
                        for (Map<String, String> patent : patentResults) {
                            String title = patent.get("title");
                            if (title != null && title.contains(searchTerm)) {
                                matchingPatents.add(patent);
                            }
                        }

                        // Filter LinkedIn results based on matched inventors
                        List<Map<String, String>> matchingLinkedin = new ArrayList<>();
                        for (Map<String, String> patent : matchingPatents) {
                            String inventorName = patent.get("inventors");

                            for (Map<String, String> linkedin : linkedinResults) {
                                String linkedinName = linkedin.get("name");
                                if (linkedinName != null && linkedinName.equals(inventorName)) {
                                    matchingLinkedin.add(linkedin);
                                }
                            }
                        }
                        // Set the final results as the body of the exchange
                        exchange.getIn().setBody(matchingLinkedin);
                    }
                })
                .to("log:myLogger?level=INFO");

// Route to retrieve patent data from a service
        from("direct:patent")
                .to("http://localhost:3000/patentservice") // Send a request to the patent service
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        // Extract the JSON response from the exchange
                        String jsonResponse = exchange.getIn().getBody(String.class);

                        // Parse the JSON array directly
                        List<Map<String, String>> patentDetails = JsonPath.read(jsonResponse, "$");

                        // Log the raw response from the service
                        exchange.getIn().setBody(patentDetails);
                    }
                })
                .to("log:myLogger?level=INFO");

// Route to retrieve LinkedIn data from a service
        from("direct:linkedin")
                .to("http://localhost:3000/linkedin") // Send a request to the LinkedIn service
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        // Extract the JSON response from the exchange
                        String jsonResponse = exchange.getIn().getBody(String.class);

                        // Parse the JSON array
                        List<Map<String, String>> dataArray = JsonPath.read(jsonResponse, "$");

                        // Log the raw response from the service
                        exchange.getIn().setBody(dataArray);
                    }
                })
                .to("log:myLogger?level=INFO");
    }
}
