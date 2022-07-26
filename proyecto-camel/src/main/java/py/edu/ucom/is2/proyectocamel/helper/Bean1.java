package py.edu.ucom.is2.proyectocamel.helper;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Component
public class Bean1 implements Processor {
	Logger logger = LoggerFactory.getLogger(Bean1.class);
	@Override
	public void process(Exchange exchange) throws Exception {
		logger.info("Mensaje modificado por Bean1");
		String body  = exchange.getIn().getBody(String.class);
		exchange.getIn().setBody(body+"1");
	}
}