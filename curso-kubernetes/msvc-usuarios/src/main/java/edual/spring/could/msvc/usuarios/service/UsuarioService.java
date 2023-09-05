package edual.spring.could.msvc.usuarios.service;

import edual.spring.could.msvc.usuarios.clients.CursoClienteRest;
import edual.spring.could.msvc.usuarios.repositories.IUsuarioRepository;
import edual.spring.could.msvc.usuarios.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Autowired
    private CursoClienteRest clientRest;

    @Transactional(readOnly = true)
    @Override
    public Usuario findByNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Usuario> listar() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Transactional
    @Override
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
        clientRest.eliminarCursoUsuarioPorId(id);
    }

    @Transactional
    @Override
    public Integer contarEmail(String email) {
        return usuarioRepository.contarEmail(email);
    }

    @Transactional
    @Override
    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Transactional
    @Override
    public List<Usuario> lisatarPorIds(Iterable<Long> ids) {
        return (List<Usuario>) usuarioRepository.findAllById(ids);
    }
}
