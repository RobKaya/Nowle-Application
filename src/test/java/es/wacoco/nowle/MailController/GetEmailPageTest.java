package es.wacoco.nowle.MailController;

import es.wacoco.nowle.Mail.Controller.GetEmailPage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetEmailPageTest {

    @Test
    public void testGettingSendMailHtml() {
        GetEmailPage controller = new GetEmailPage();
        String viewName = controller.gettingSendMailHtml();
        assertEquals("mail", viewName, "The view name should be 'mail'");
    }
}
