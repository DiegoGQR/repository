package py.edu.ucom.is2.proyectocamel.helper;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class Examen extends RouteBuilder{

	@Autowired
	
	@Override
    public void configure() throws Exception {
        from("timer:mytimer?period=2000")
        .transform().simple("Mensaje original")
        .multicast().parallelProcessing()
            .pipeline()
                .to("bean:bean1")
                 .to("log:logbean1")
            .end()
            .pipeline()
                .to("bean:bean2")
                  .to("log:logbean2")
            .end()
            .to("log:loggerFinal")
        .end();
    }
}
