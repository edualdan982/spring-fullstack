package edual.spring.could.msvc.auth.services;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import edual.spring.could.msvc.auth.models.Usuario;

@Service
public class UserService implements UserDetailsService {
  private Logger log = LoggerFactory.getLogger(UserService.class);

  @Autowired
  private WebClient client;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    try {
      Usuario usuario = client.get().uri("http://msvc-usuarios:8001/login", uri -> uri.queryParam("email", email).build())
          .accept(MediaType.APPLICATION_JSON)
          .retrieve()
          .bodyToMono(Usuario.class)
          .block();
      log.info("Usuario login: {}", usuario.getEmail());
      log.info("Usuario nombre: {}", usuario.getNombre());
      log.info("Usuario password: {}", usuario.getPassword());

      return new User(email, usuario.getPassword(), true, true, true, true,
          Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
    } catch (RuntimeException e) {
      String error = "Error en login, no existe el usuario " + email;
      log.error(e.getMessage() == null ? "Sin detalle del error." : e.getMessage());
      throw new UsernameNotFoundException(error);
    }
  }

}
