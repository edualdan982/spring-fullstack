package edual.spring.could.msvc.cursos.services;

import edual.spring.could.msvc.cursos.clients.UsuarioClientRest;
import edual.spring.could.msvc.cursos.models.Usuario;
import edual.spring.could.msvc.cursos.models.entity.Curso;
import edual.spring.could.msvc.cursos.models.entity.CursoUsuario;
import edual.spring.could.msvc.cursos.repositories.CursoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CursoServiceImpl implements CursoService {
    private static final Logger log = LoggerFactory.getLogger(CursoServiceImpl.class);

    @Autowired
    private CursoRepository repository;
    @Autowired
    private UsuarioClientRest usuarioClientRest;

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

    @Transactional(readOnly = true)
    @Override
    public Optional<Curso> porIdConUsuarios(Long id, String token) {
        Optional<Curso> o = repository.findById(id);
        if (o.isPresent()) {
            log.info("Se ha encontrado el curso {}", id);
            Curso curso = o.get();
            if (!curso.getCursoUsuarios().isEmpty()) {
                List<Long> ids = curso.getCursoUsuarios().stream().map(cu -> cu.getUsuarioId())
                        .collect(Collectors.toList());
                log.info("Se han convertido los ids {}", ids.toString());
                List<Usuario> usuarios = usuarioClientRest.obtenerAlumnosPorCurso(ids, token);
                log.info("Cantidad de usuarios: {}", usuarios.size());
                curso.setUsuarios(usuarios);
            }
            return Optional.of(curso);
        }
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
    public void eliminarCursoUsuarioPorId(Long id) {
        repository.eliminarCursoUsuarioPorId(id);
    }

    @Transactional
    @Override
    public Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId, String token) {
        Optional<Curso> o = repository.findById(cursoId);
        if (o.isPresent()) {
            Usuario usuarioMsvc = usuarioClientRest.detalle(usuario.getId(), token);
            Curso curso = o.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioMsvc.getId());

            curso.addCursoUsuario(cursoUsuario);
            repository.save(curso);

            return Optional.ofNullable(usuarioMsvc);
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId, String token) {
        Optional<Curso> o = repository.findById(cursoId);
        if (o.isPresent()) {
            Usuario usuarioNuevoMsvc = usuarioClientRest.crear(usuario, token);
            Curso curso = o.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioNuevoMsvc.getId());

            curso.addCursoUsuario(cursoUsuario);
            repository.save(curso);

            return Optional.ofNullable(usuarioNuevoMsvc);
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId, String token) {
        log.info("Removiendo el usuario {} del curso: {}", usuario.getId(), cursoId);
        Optional<Curso> o = repository.findById(cursoId);
        if (o.isPresent()) {
            Usuario usuarioNuevoMsvc = usuarioClientRest.detalle(usuario.getId(), token);
            Curso curso = o.get();
            CursoUsuario cursoUsuario = new CursoUsuario();
            cursoUsuario.setUsuarioId(usuarioNuevoMsvc.getId());

            curso.removeCursoUsuario(cursoUsuario);
            log.info("Lista usuarios: {}", curso.getCursoUsuarios().size());
            repository.save(curso);

            return Optional.ofNullable(usuarioNuevoMsvc);
        }
        return Optional.empty();
    }
}
