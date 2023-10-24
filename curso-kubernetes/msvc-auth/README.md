### Configuración de Servidor de Autorización de Autenticación

#### 1.- 
  Una cadena de filtros de Spring Security para los puntos finales del protocolo.
  https://docs.spring.io/spring-authorization-server/docs/current/reference/html/protocol-endpoints.html

#### 2.- 
Una cadena de filtros de Spring Security para la autenticación.
https://docs.spring.io/spring-security/reference/servlet/authentication/index.html

#### 3.- 
Una instancia de UserDetailsService para recuperar usuarios para autenticarse.
https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/core/userdetails/UserDetailsService.html

#### 4.- 
Una instancia de RegisteredClientRepository para administrar clientes.
https://docs.spring.io/spring-authorization-server/docs/current/reference/html/core-model-components.html#registered-client-repository

#### 5.- 
Una instancia de para firmar tokens de acceso ``` com.nimbusds.jose.jwk.source.JWKSource ```

#### 6.- 
Una instancia de con claves generadas en el inicio que se utiliza para crear lo anterior ``` java.security.KeyPairJWKSource ```

#### 7.- 
Una instancia de JwtDecoder para descodificar tokens de acceso firmados.
https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/oauth2/jwt/JwtDecoder.html

#### 8.- 
Una instancia de AuthorizationServerSettings para configurar el servidor de autorización de Spring.
https://docs.spring.io/spring-authorization-server/docs/current/reference/html/configuration-model.html#configuring-authorization-server-settings