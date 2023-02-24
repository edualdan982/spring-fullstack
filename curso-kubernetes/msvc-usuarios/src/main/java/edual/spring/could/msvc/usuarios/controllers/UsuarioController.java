package edual.spring.could.msvc.usuarios.controllers;

import edual.spring.could.msvc.usuarios.entity.Usuario;
import edual.spring.could.msvc.usuarios.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listar(){
        return usuarioService.listar();
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Optional<Usuario> usuarioOptional = usuarioService.findById(id);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Usuario usuario){
      return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardar(usuario));
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody Usuario usuario, @PathVariable Long id){
        Optional<Usuario> usuarioActualizar = usuarioService.findById(id);
        if(usuarioActualizar.isPresent()){
            usuarioActualizar.get().setNombre(usuario.getNombre());
            usuarioActualizar.get().setEmail(usuario.getEmail());
            usuarioActualizar.get().setEstado(usuario.getEstado());
            usuarioActualizar.get().setPassword(usuario.getPassword());

            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardar(usuarioActualizar.get()));
        } else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Usuario> o = usuarioService.findById(id);
        if(o.isPresent()){
            usuarioService.deleteById(id);
            return  ResponseEntity.noContent().build();
        }
        return  ResponseEntity.notFound().build();
    }
}
