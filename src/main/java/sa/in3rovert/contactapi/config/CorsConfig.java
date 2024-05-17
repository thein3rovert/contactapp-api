package sa.in3rovert.contactapi.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpHeaders.ORIGIN;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static sa.in3rovert.contactapi.constant.Constant.X_REQUESTED_WITH;

@Configuration
public class CorsConfig {

    /**
     * Generates a CorsFilter bean for handling Cross-Origin Resource Sharing (CORS) configuration.
     *
     * @return         	an instance of CorsFilter for CORS configuration
     */
    @Bean
// Annotation to indicate that this method will create a bean in the Spring application context
    public CorsFilter corsFilter() {
        // Method to create a CorsFilter bean for handling CORS configuration
        var urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        // Create an instance of UrlBasedCorsConfigurationSource for configuring CORS based on URL patterns
        var corsConfiguration = new CorsConfiguration();
        // Create a new CorsConfiguration object for setting up CORS configurations
        corsConfiguration.setAllowCredentials(true);
        // Set whether the browser should include any cookies in the request
        corsConfiguration.setAllowedOrigins(List.of("http://localhost:3000", "http://192.168.0.10:3000"));
        // Set the allowed origins for CORS requests

        corsConfiguration.setAllowedHeaders(List.of(ORIGIN, ACCESS_CONTROL_ALLOW_ORIGIN, CONTENT_TYPE, ACCEPT, AUTHORIZATION, X_REQUESTED_WITH, ACCESS_CONTROL_REQUEST_METHOD, ACCESS_CONTROL_REQUEST_HEADERS, ACCESS_CONTROL_ALLOW_CREDENTIALS));
        // Set the allowed headers for CORS requests
        corsConfiguration.setExposedHeaders(List.of(ORIGIN, ACCESS_CONTROL_ALLOW_ORIGIN, CONTENT_TYPE, ACCEPT, AUTHORIZATION, X_REQUESTED_WITH, ACCESS_CONTROL_REQUEST_METHOD, ACCESS_CONTROL_REQUEST_HEADERS, ACCESS_CONTROL_ALLOW_CREDENTIALS));
        // Set the headers that are exposed to the browser
        corsConfiguration.setAllowedMethods(List.of(GET.name(), POST.name(), PUT.name(), PATCH.name(), DELETE.name(), OPTIONS.name()));
        // Set the allowed HTTP methods for CORS requests
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        // Register the CorsConfiguration with a URL pattern of "/**"
        return new CorsFilter(urlBasedCorsConfigurationSource);
        // Return a new instance of CorsFilter initialized with the UrlBasedCorsConfigurationSource
    }
}
