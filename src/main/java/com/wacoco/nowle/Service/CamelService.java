package com.wacoco.nowle.Service;

import com.wacoco.nowle.Controller.CamelController;
import org.apache.camel.CamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CamelService {
    private static final Logger logger= LoggerFactory.getLogger(CamelController.class);
    private final CamelContext camelContext;

    public CamelService(CamelContext camelContext) {
        this.camelContext = camelContext;
    }
    public String patentRoute(){
        return "";
    }
    public String linkedinRoute(){
        return "";
    }

}
