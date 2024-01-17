package es.wacoco.nowle.Camel.Service;

import es.wacoco.nowle.Camel.Controller.CamelController;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
          return "Camel route process initiated! Response from patent server:\n" + response +"\n:Date"+getFormattedTimestamp();
        }catch (Exception e){
            e.printStackTrace();
            return "Error occurred in getting the patent data ";

        }

    }
    public String linkedinRoute(){
        try {
            ProducerTemplate producerTemplate= camelContext.createProducerTemplate();
            String response = producerTemplate.requestBody("direct:linkedin",null, String.class);
            return "Camel route process initiated! Response from linkedin server:\n"+response+"\n:Date"+getFormattedTimestamp();
        }catch (Exception e) {
            e.printStackTrace();
            return "Error occurred in getting the linkedin data ";
        }
    }
    private String getFormattedTimestamp() {
        // Get the current timestamp using a custom formatter without milliseconds
        LocalDateTime timestamp = LocalDateTime.now();
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timestamp.format(customFormatter);
    }

}
