package es.wacoco.nowle.RouteTesting;


import es.wacoco.nowle.Camel.Routes.CamelRoutes;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.test.junit4.CamelTestSupport;

import static org.apache.camel.builder.AdviceWith.adviceWith;

public class PatentServiceRouterTest extends CamelTestSupport {
@Override
    public boolean isUseAdviceWith(){
    return true;
}
@Override
    public RouteBuilder createRouteBuilder()throws Exception{
    return new CamelRoutes();
}
public void TestMockEndpoints()throws Exception{
    RouteDefinition route= context.getRouteDefinition("");
    adviceWith(route, context, new AdviceWithRouteBuilder() {
        @Override
        public void configure() throws Exception {
            weaveAddLast().to("mock:");
        }
    });
}


}


