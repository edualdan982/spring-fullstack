package edual.spring.could.msvc.usuarios.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-cursos"
// , url = "host.docker.internal:8002/curso"
// , url = "msvc-cursos:8002/curso"
        , url = "${msvc.cursos.url}")
public interface CursoClienteRest {

    @DeleteMapping("/eliminar-curso-usuario/{id}")
    void eliminarCursoUsuarioPorId(@PathVariable Long id);
}
