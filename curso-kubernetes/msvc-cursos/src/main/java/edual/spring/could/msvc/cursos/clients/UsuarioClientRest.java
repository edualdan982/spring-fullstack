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

    @GetMapping("/usuario/{id}")
    Usuario detalle(@PathVariable Long id);

    @PostMapping("/usuario")
    Usuario crear(@RequestBody Usuario usuarioReq);

    @GetMapping("/usuario/usuarios-por-curso")
    List<Usuario> obtenerAlumnosPorCurso(@RequestParam Iterable<Long> ids);
}
