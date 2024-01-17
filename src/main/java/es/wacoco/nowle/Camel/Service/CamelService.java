package es.wacoco.nowle.Camel.Service;

import es.wacoco.nowle.Camel.Controller.CamelController;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CamelService {
    private static final Logger logger= LoggerFactory.getLogger(CamelController.class);
    private final CamelContext camelContext;

    public CamelService(CamelContext camelContext) {
        this.camelContext = camelContext;
    }
    public String patentServiceRoute(){
        try{
            ProducerTemplate producerTemplate= camelContext.createProducerTemplate();
            String response=producerTemplate.requestBody("direct:patent",null, String.class);
          return "Camel route process initiated! Response from patent server:\n" + response +"\n Date" + getFormattedTimestamp();
        }catch (Exception e){
            e.printStackTrace();
            return "Error occurred in getting the patent data ";

        }

    }

    public String linkedinRoute(){
        try {
            ProducerTemplate producerTemplate= camelContext.createProducerTemplate();
            String response=producerTemplate.requestBody("direct:linkedin",null, String.class);
            return "Camel route process initiated! Response from linkedin server:\n" + response + "\n Date"+getFormattedTimestamp();
        }catch (Exception e) {
            e.printStackTrace();
            return "Error occurred in getting the linkedin data ";
        }
    }

    /*
    * the problem is the search is working but what it is finding is the full array witch is bad,
    * we neeed some how to make sure its not finding and returning the full array, it should only return json block.
    * */
    public String searchRoute(String searchTerm) {
        try {
            // Create a map to store the search results from both services
            Map<String, Object> searchResults = new HashMap<>();

            System.out.println(searchResults);
            // Invoke the patent service route
            String patentResponse = camelContext.createProducerTemplate().requestBody("direct:patent", null, String.class);
            searchResults.put("patent", patentResponse);
//            System.out.println("this is patent route \n" + patentResponse);
            // Invoke the LinkedIn service route
            String linkedinResponse = camelContext.createProducerTemplate().requestBody("direct:linkedin", null, String.class);
            searchResults.put("linkedin", linkedinResponse);
//            System.out.println("and this is lindkenin came route data \n" + linkedinResponse);
            // For simplicity, let's assume a match if the searchTerm is found in either response
            List<String> matchingResults = new ArrayList<>();
            if (patentResponse.contains(searchTerm)) {
                matchingResults.add("Match found in patent response:\n" + patentResponse);
            }
            if (linkedinResponse.contains(searchTerm)) {
                matchingResults.add("Match found in LinkedIn response:\n" + linkedinResponse);
            }
            System.out.println("this is match resuult at the top: \n: " + matchingResults);
            // Return the search results
            return "Search results for term '" + searchTerm + "':\n" + String.join("\n", matchingResults) + "\n Date" + getFormattedTimestamp();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred during search";
        }
    }
    private String getFormattedTimestamp() {
        // Get the current timestamp using a custom formatter without milliseconds
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timestamp.format(customFormatter);
    }

}
