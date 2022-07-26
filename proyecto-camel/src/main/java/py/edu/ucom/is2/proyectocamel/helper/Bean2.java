package py.edu.ucom.is2.proyectocamel.helper;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Component
public class Bean2 implements Processor {
	Logger logger = LoggerFactory.getLogger(Bean2.class);
	@Override
	public void process(Exchange exchange) throws Exception {
		logger.info("Mensaje modificado por Bean2");
		String body  = exchange.getIn().getBody(String.class);
		exchange.getIn().setBody(body+"2");
	}

}