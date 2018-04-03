package in.pickmyclick.security;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import in.pickmyclick.config.TechTutoConfiguration;

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { TechTutoConfiguration.class };
	}
 
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}
 
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
