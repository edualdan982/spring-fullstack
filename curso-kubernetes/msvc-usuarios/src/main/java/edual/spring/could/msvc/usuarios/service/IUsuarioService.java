package edual.spring.could.msvc.usuarios.service;

import edual.spring.could.msvc.usuarios.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    public Usuario findByNombre(String nombre);

    public List<Usuario> listar();

    public Optional<Usuario> findById(Long id);

    public Usuario guardar(Usuario usuario);

    public void deleteById(Long id);
}
