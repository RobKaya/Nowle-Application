package es.wacoco.nowle.Camel.Controller;

import es.wacoco.nowle.Camel.Service.CamelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dev")
public class CamelController {
    private final CamelService camelService;

    @Autowired
    public CamelController(CamelService camelService) {
    this.camelService = camelService;

    }

    @GetMapping("/patent")
    public String getPatentService(){
        return camelService.patentServiceRoute();
    }

    @GetMapping("/search")
    public String search(@RequestParam String searchTerm) {
        return camelService.searchRoute(searchTerm);
    }

    @GetMapping("/linkedin")
    public String getLinkedin(){
    return camelService.linkedinRoute();
    }
}
