package io.github.gaosups.qqi.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * This class is responsible for configuring and providing the message source for localization in a web application.
 * It implements {@link WebMvcConfigurer} to integrate with Spring MVC.
 *
 * <p>The configuration includes setting up a message source for localization and configuring a validator to use the message source for validation messages.</p>
 *
 * <p>The message source is configured to use resource bundles located in the "languages" directory with the base name "messages" and UTF-8 encoding.</p>
 *
 * @since 1.2
 * @see WebMvcConfigurer
 * @see LocalValidatorFactoryBean
 * @see ResourceBundleMessageSource
 *
 * @author 0.1.0-alpha-1
 */
@Configuration
public class MessageConfig implements WebMvcConfigurer {

	/**
	 * Creates a {@link LocalValidatorFactoryBean} that is responsible for validating objects using a message source for localization.
	 *
	 * @param messageSource the message source used for localization
	 * @return a {@link LocalValidatorFactoryBean} configured with the provided message source
	 */
	@Bean
	public LocalValidatorFactoryBean validator(MessageSource messageSource) {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setValidationMessageSource(messageSource);
		return validator;
	}

	/**
	 * Creates and configures a message source for localization in a web application.
	 *
	 * <p>The message source is configured to use resource bundles located in the "languages" directory with the base name "messages".</p>
	 * <p>The default encoding for the message source is set to UTF-8.</p>
	 *
	 * @return the configured {@link MessageSource} object
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("languages/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
}