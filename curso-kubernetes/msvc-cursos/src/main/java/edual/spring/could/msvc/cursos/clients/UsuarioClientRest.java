package edual.spring.could.msvc.cursos.clients;

import edual.spring.could.msvc.cursos.models.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "msvc-usuarios"
// ,url = "host.docker.internal:8001/usuario"
// ,url = "msvc-usuarios:8001/usuario"
// , url = "${msvc.usuarios.url}"
)
public interface UsuarioClientRest {

    @GetMapping("/{id}")
    Usuario detalle(@PathVariable Long id, @RequestHeader(value = "Authorization", required = true) String token);

    @PostMapping("/")
    Usuario crear(@RequestBody Usuario usuarioReq,
            @RequestHeader(value = "Authorization", required = true) String token);

    @GetMapping("/usuarios-por-curso")
    List<Usuario> obtenerAlumnosPorCurso(@RequestParam Iterable<Long> ids,
            @RequestHeader(value = "Authorization", required = true) String token);
}
