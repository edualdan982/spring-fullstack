package edual.spring.could.msvc.usuarios;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
  private static final String SCOPE_WRITE = "SCOPE_write"; 
  private static final String SCOPE_READ = "SCOPE_read"; 
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
      httpSecurity.authorizeHttpRequests().requestMatchers("/authorized", "/actuator/**").permitAll()
              .requestMatchers(HttpMethod.GET, "/")
              .hasAnyAuthority(SCOPE_READ, SCOPE_WRITE)
              .requestMatchers(HttpMethod.POST, "/").hasAuthority(SCOPE_WRITE)
              .requestMatchers(HttpMethod.PUT, "/{id}").hasAuthority(SCOPE_WRITE)
              .requestMatchers(HttpMethod.DELETE, "/").hasAuthority(SCOPE_WRITE)
              .anyRequest().authenticated()
              .and().sessionManagement(
              management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .oauth2Login(oauth2Login -> oauth2Login.loginPage("/oauth2/authorization/msvc-usuarios-client"))
              .oauth2Client(withDefaults())
              .oauth2ResourceServer(server -> server.jwt());
    return httpSecurity.build();
  }
}
