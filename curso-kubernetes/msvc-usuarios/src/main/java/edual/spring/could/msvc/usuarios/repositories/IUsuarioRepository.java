package edual.spring.could.msvc.usuarios.repositories;

import edual.spring.could.msvc.usuarios.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface IUsuarioRepository extends CrudRepository<Usuario, Long> {
    public Usuario findByNombre(String nombre);

    @Query(value = "SELECT count(u) from Usuario u WHERE u.email = :email")
    public Integer contarEmail(String email);

    @Query(value = "SELECT u FROM Usuario u WHERE u.email = :email")
    public Optional<Usuario> findByEmail(String email);

}
