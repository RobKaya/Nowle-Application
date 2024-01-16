package com.wacoco.nowle.Routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelRoutes extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct:patent")
                .to("http://localhost:3000/patent")
                .log("Data retrieved from Patent server: ${body}")
                .to("log:myLogger?level=INFO");
        from("direct:linkedin")
                .to("http://localhost:3000/linkedin")
                .log("Data retrieved from Linkedin server :${body}")
                .to("log:myLogger?level=INFO");

    }
}
