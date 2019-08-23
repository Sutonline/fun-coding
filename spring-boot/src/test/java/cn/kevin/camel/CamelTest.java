package cn.kevin.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.Test;

/**
 * @author yongkang.zhang
 * created at 22/08/2019
 */
public class CamelTest {

	@Test
	public void firstCamel() throws Exception {
		CamelContext context = new DefaultCamelContext();
		context.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("file:data/inbox?noop=true")
						.to("file:data/outbox");
			}
		});
	}

}
