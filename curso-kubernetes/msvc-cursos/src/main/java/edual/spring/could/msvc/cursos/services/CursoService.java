package edual.spring.could.msvc.cursos.services;

import edual.spring.could.msvc.cursos.models.Usuario;
import edual.spring.could.msvc.cursos.models.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    List<Curso> listar();

    Optional<Curso> porId(Long id);

    Optional<Curso> porIdConUsuarios(Long id, String token);

    Curso guardar(Curso curso);

    void eliminar(Long id);

    void eliminarCursoUsuarioPorId(Long id);

    Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId, String token);

    Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId, String token);

    Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId, String token);
}
