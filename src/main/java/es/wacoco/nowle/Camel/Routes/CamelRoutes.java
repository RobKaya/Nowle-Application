package es.wacoco.nowle.Camel.Routes;

import com.jayway.jsonpath.JsonPath;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CamelRoutes extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:patentService")
                .to("http://localhost:3000/patentService")
                .log("Data retrieved from Patent server: ${body}")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        String jsonResponse= exchange.getIn().getBody(String.class);
                        String title= JsonPath.read(jsonResponse,"$.title");
                        List<Map<String,String>>patentDetails=JsonPath.read(jsonResponse,"$.patentDetails");
                        StringBuilder filteredData=new StringBuilder("Title:"+title+"\n");
                        for(Map<String,String>patent:patentDetails){
                            String description=patent.get("description");
                            String applicant=patent.get("applicant");
                            String inventors=patent.get("inventors");
                            filteredData.append("Description:").append("description")
                                    .append("\n")
                                    .append("Applicant:").append("applicant")
                                    .append("\n")
                                    .append("Inventors").append("inventors");
                        }
                        exchange.getIn().setBody(filteredData.toString());
                    }
                })
                .to("log:myLogger?level=INFO");
        from("direct:linkedin")
                .to("http://localhost:3000/linkedin")
                .log("Data retrieved from Linkedin server :${body}")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                       String jsonResponse=exchange.getIn().getBody(String.class);
                       String name=JsonPath.read(jsonResponse,"$.name");
                       String email=JsonPath.read(jsonResponse,"$.email");
                       String filteredData= "name:"+name+ ",email:"+email;
                       exchange.getIn().setBody(filteredData);
                    }
                })
                .to("log:myLogger?level=INFO");
    }
}
