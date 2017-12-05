package com.eadydb.tcc.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement(order = 10)
@ImportResource({"classpath:dubbo/*.xml","classpath:tcc-transaction.xml","classpath:tcc-transaction-dubbo.xml"})
public class TccDubboOrderApplication extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(TccDubboOrderApplication.class);
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {

	}

	public static void main(String[] args) {
		SpringApplication.run(TccDubboOrderApplication.class, args);
	}
}
