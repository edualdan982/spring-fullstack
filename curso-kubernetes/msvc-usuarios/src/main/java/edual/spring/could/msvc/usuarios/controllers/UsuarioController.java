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

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public List<Usuario> listar(){
        return usuarioService.listar();
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Usuario usuario){
        Map<String, Object> response = new HashMap<>();
        response.put("usuario", usuarioService.guardar(usuario));
        response.put("mensaje", "Se ha registro un usuario");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        response.put("usuario", usuarioService.findById(id));
        response.put("mensaje", "Se ha registro un usuario");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody Usuario usuario){
        Map<String, Object> response = new HashMap<>();
        if(usuario == null){
            response.put("mensaje", "El usuario a actualizar es nulo");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        Usuario usuarioActualizar = usuarioService.findById(usuario.getId()).orElse(null);
        response.put("usuario", usuarioService.guardar(usuario));
        response.put("mensaje", "Se ha registro un usuario");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
