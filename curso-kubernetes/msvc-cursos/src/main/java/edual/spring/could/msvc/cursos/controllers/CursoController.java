package edual.spring.could.msvc.cursos.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edual.spring.could.msvc.cursos.models.Usuario;
import edual.spring.could.msvc.cursos.models.entity.Curso;
import edual.spring.could.msvc.cursos.services.CursoService;
import feign.FeignException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/curso")
public class CursoController {
    private static final String MENSAJE = "mensaje";

    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<Object> listar() {
        return ResponseEntity.ok(cursoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> porId(@PathVariable Long id,
            @RequestHeader(value = "Authorization", required = true) String token) {
        Optional<Curso> o = cursoService.porIdConUsuarios(id, token);
        if (o.isPresent())
            return ResponseEntity.ok(o.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Curso curso, BindingResult result) {
        if (result.hasErrors()) {
            return validar(result);
        }

        Curso cursoDb = cursoService.guardar(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoDb);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Curso curso, BindingResult result, @PathVariable Long id) {
        Optional<Curso> o = cursoService.porId(id);
        if (result.hasErrors()) {
            return validar(result);
        }
        if (o.isPresent()) {
            Curso cursoDb = o.get();
            cursoDb.setNombre(curso.getNombre());
            cursoService.guardar(cursoDb);
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoDb);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> eliminar(@PathVariable Long id) {
        Optional<Curso> o = cursoService.porId(id);
        if (o.isPresent()) {
            cursoService.eliminar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/asignar-usuario/{cursoId}")
    public ResponseEntity<Object> asignarUsuario(@RequestBody Usuario usuario, @PathVariable Long cursoId,
            @RequestHeader(value = "Authorization", required = true) String token) {
        Optional<Usuario> o;
        try {
            o = cursoService.asignarUsuario(usuario, cursoId, token);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                            Collections.singletonMap(MENSAJE,
                                    "No existe el usuario por id o error en la comunicacion: Detalle: "
                                            + e.getMessage()));
        }
        if (o.isPresent())
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/crear-usuario/{cursoId}")
    public ResponseEntity<Object> crearUsuario(@RequestBody Usuario usuario, @PathVariable Long cursoId,
            @RequestHeader(value = "Authorization", required = true) String token) {
        Optional<Usuario> o;
        try {
            o = cursoService.crearUsuario(usuario, cursoId, token);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                            Collections.singletonMap(MENSAJE,
                                    "No se pudo crear el usuario o error en la comunicacion: Detalle: "
                                            + e.getMessage()));
        }
        if (o.isPresent())
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar-usuario/{cursoId}")
    public ResponseEntity<Object> eliminarUsuario(@RequestBody Usuario usuario, @PathVariable Long cursoId,
            @RequestHeader(value = "Authorization", required = true) String token) {
        Optional<Usuario> o;
        try {
            o = cursoService.eliminarUsuario(usuario, cursoId, token);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                            Collections.singletonMap(MENSAJE,
                                    "No se pudo eliminar el usuario por el id o error en la comunicacion: Detalle: "
                                            + e.getMessage()));
        }
        if (o.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(o.get());
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar-curso-usuario/{id}")
    public ResponseEntity<Object> eliminarCursoUsuarioPorId(@PathVariable Long id) {
        cursoService.eliminarCursoUsuarioPorId(id);
        return ResponseEntity.noContent().build();
    }

    private static ResponseEntity<Map<String, String>> validar(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> errores.put(err.getField(),
                String.format("El campo %s %s", err.getField(), err.getDefaultMessage())));
        return ResponseEntity.badRequest().body(errores);
    }
}
