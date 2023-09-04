package edual.spring.could.msvc.cursos.services;

import edual.spring.could.msvc.cursos.models.Usuario;
import edual.spring.could.msvc.cursos.models.entity.Curso;
import edual.spring.could.msvc.cursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServiceImpl implements CursoService {
    @Autowired
    private CursoRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Curso> listar() {
        return (List<Curso>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Curso> porId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Curso guardar(Curso curso) {
        return repository.save(curso);
    }

    @Transactional
    @Override
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public Optional<Usuario> asignarUsuario(Usuario usuario, Long id) {
        return Optional.empty();
    }
    @Transactional
    @Override
    public Optional<Usuario> crearUsuario(Usuario usuario, Long id) {
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId) {
        return Optional.empty();
    }
}
