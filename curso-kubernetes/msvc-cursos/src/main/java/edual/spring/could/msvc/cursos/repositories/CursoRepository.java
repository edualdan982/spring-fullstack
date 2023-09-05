package edual.spring.could.msvc.cursos.repositories;

import edual.spring.could.msvc.cursos.models.entity.Curso;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long> {
    @Modifying
    @Query("delete from CursoUsuario cu WHERE cu.usuarioId=?1")
    void eliminarCursoUsuarioPorId(Long id);
}
