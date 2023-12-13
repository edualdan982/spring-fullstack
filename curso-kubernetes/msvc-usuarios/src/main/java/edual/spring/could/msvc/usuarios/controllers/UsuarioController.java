package edual.spring.could.msvc.usuarios.controllers;

import edual.spring.could.msvc.usuarios.entity.Usuario;
import edual.spring.could.msvc.usuarios.service.IUsuarioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class UsuarioController {
    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);
    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ApplicationContext context;
    @Autowired
    private Environment env;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/crash")
    public void crash() {
        ((ConfigurableApplicationContext) context).close();
    }

    @GetMapping("/info")
    public String info() {
        return "Esta arriba el servicio msvc-usuarios";
    }

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> listar() {
        Map<String, Object> body = new HashMap<>();
        body.put("podinfo", env.getProperty("MY_POD_NAME") + ": " + env.getProperty("MY_POD_IP"));
        body.put("users", usuarioService.listar());
        body.put("texto", env.getProperty("config.texto"));
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> detalle(@PathVariable Long id) {
        log.info("Se esta consumiendo el controlador usuario-detalle...");
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<?> crear(@RequestBody @Valid Usuario usuarioReq, BindingResult result) {
        log.info("Se esta consumiendo el controlador usuario-crear...");
        usuarioReq.setId(null);
        if (result.hasErrors()) {
            return validar(result);
        }
        if (usuarioService.findByEmail(usuarioReq.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("mensaje", "El email ya esta en uso."));
        }
        usuarioReq.setPassword(passwordEncoder.encode(usuarioReq.getPassword()));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardar(usuarioReq));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Usuario usuario, BindingResult result,
            @PathVariable Long id) {
        if (result.hasErrors()) {
            return validar(result);
        }
        Optional<Usuario> usuarioActualizar = usuarioService.findById(id);
        if (usuarioActualizar.isPresent()) {
            if (!usuario.getEmail().equalsIgnoreCase(usuarioActualizar.get().getEmail())
                    && usuarioService.findByEmail(usuario.getEmail()).isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Collections.singletonMap("mensaje", "El email ya esta en uso."));
            }
            usuarioActualizar.get().setNombre(usuario.getNombre());
            usuarioActualizar.get().setEmail(usuario.getEmail());
            usuarioActualizar.get().setEstado(usuario.getEstado());
            usuarioActualizar.get().setPassword(passwordEncoder.encode(usuario.getPassword()));
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardar(usuarioActualizar.get()));
        } else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Usuario> o = usuarioService.findById(id);
        if (o.isPresent()) {
            usuarioService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/usuarios-por-curso")
    public ResponseEntity<?> obtenerAlumnosPorCurso(@RequestParam List<Long> ids) {
        return ResponseEntity.ok(usuarioService.lisatarPorIds(ids));
    }

    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errores.put(err.getField(), String.format("El campo %s %s", err.getField(), err.getDefaultMessage()));
        });
        return ResponseEntity.badRequest().body(errores);
    }

    @GetMapping("/authorized")
    public Map<String, Object> autorized(@RequestParam String code) {
        return Collections.singletonMap("code", code);
    }

    @GetMapping("/login")
    public ResponseEntity<Object> loginByEmail(@RequestParam(name = "email") String email) {

        Optional<Usuario> o = usuarioService.findByEmail(email);
        if (o.isPresent()) {
            return ResponseEntity.ok(o.get());
        } else
            return ResponseEntity.notFound().build();

    }

}
