package edual.spring.could.msvc.usuarios.repositories;

import edual.spring.could.msvc.usuarios.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioRepository extends CrudRepository<Usuario, Long> {

    public Usuario findByNombre(String nombre);
}
