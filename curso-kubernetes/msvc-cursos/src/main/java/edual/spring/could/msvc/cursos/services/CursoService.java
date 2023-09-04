package edual.spring.could.msvc.cursos.services;

import edual.spring.could.msvc.cursos.models.Usuario;
import edual.spring.could.msvc.cursos.models.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    public List<Curso> listar();
    public Optional<Curso> porId(Long id);
    public Optional<Curso> porIdConUsuarios(Long id);
    public Curso guardar(Curso curso);
    public void eliminar(Long id);
    public Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId);
    public Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId);
    public Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId);
}
