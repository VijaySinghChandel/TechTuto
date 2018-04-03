package in.pickmyclick.config;

import java.sql.SQLException;
import java.util.Locale;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "in.pickmyclick")
public class TechTutoConfiguration extends WebMvcConfigurerAdapter {

	@Bean(name="TechTuto")
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	/*
	 * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//  registry.addResourceHandler("/js/**").addResourceLocations("/resources/mytheme/js");
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/mytheme/");
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/i18/msg");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	@Bean
	public LocaleResolver localeResolver(){
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(new Locale("en"));
		resolver.setCookieName("myLocaleCookie");
		resolver.setCookieMaxAge(4800);
		return resolver;
	}

/*	@Bean(name="dataSource")
	public DataSource getDataSource() throws SQLException {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3307/pickmyclickdb");
		dataSource.setUsername("newuser");
		dataSource.setPassword("password");
		dataSource.setDefaultAutoCommit(false);
		return dataSource;
	}
*/
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dataSource);
		return dataSourceTransactionManager;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("mylocale");
		registry.addInterceptor(interceptor);
	}


}