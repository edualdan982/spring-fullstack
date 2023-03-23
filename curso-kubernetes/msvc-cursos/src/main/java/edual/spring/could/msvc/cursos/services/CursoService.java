package edual.spring.could.msvc.cursos.services;

import edual.spring.could.msvc.cursos.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {
    public List<Curso> listar();

    public Optional<Curso> porId(Long id);

    public Curso guardar(Curso curso);

    public void eliminar(Long id);
}
