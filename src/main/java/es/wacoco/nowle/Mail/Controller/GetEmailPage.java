package es.wacoco.nowle.Mail.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/email")
public class GetEmailPage {
    @GetMapping("/send")
    public String gettingSendMailHtml(){
        return "mail";
    }
}
