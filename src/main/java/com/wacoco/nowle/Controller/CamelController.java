package com.wacoco.nowle.Controller;

import com.wacoco.nowle.Service.CamelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CamelController {

    private final CamelService camelService;
@Autowired
    public CamelController(CamelService camelService) {
        this.camelService = camelService;
    }

    @GetMapping("/patent")
    public String getPatent(){
        return camelService.patentRoute();
    }
    @GetMapping("/linkedin")
    public String getLinkedin(){
    return camelService.linkedinRoute();



    }
}
