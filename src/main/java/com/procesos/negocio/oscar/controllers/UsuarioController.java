package com.procesos.negocio.oscar.controllers;

import com.procesos.negocio.oscar.models.Usuario;
import com.procesos.negocio.oscar.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "/usuario/{id}")
    public Optional<Usuario> getUsuario(@PathVariable Long id){
       Optional<Usuario> usuario= usuarioRepository.findById(id);
       return usuario;

    }

    @PostMapping("/usuario")
    public Usuario crearUsuario(@RequestBody Usuario usuario){
        usuarioRepository.save(usuario);
        return usuario;
    }

    @GetMapping(value = "/all")
    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    @GetMapping("/usuario/{nombre}/{apellidos}")
    public List<Usuario> listarPorNombreApellidos(@PathVariable String nombre,@PathVariable String apellidos){
        return usuarioRepository.findAllByNombreAndApellidos(nombre, apellidos);
    }

    @GetMapping("/usuario/nombre/{nombre}")
    public List<Usuario> listarPorNombre(@PathVariable String nombre){
        return usuarioRepository.findAllByNombre(nombre);
    }

    @GetMapping("/usuario/apellido/{apellidos}")
    public List<Usuario> listarPorApellido(@PathVariable String apellidos){
        return usuarioRepository.findAllByApellidos(apellidos);
    }

    @PutMapping("/usuario/{id}")
    public Usuario editarUsuario(@PathVariable Long id,@RequestBody Usuario usuario){
        Usuario usuarioBd= usuarioRepository.findById(id).get();
        try {
            usuarioBd.setNombre(usuario.getNombre());
            usuarioBd.setApellidos(usuario.getApellidos());
            usuarioBd.setDireccion(usuario.getDireccion());
            usuarioBd.setDocumento(usuario.getDocumento());
            usuarioBd.setFechaNacimiento(usuario.getFechaNacimiento());
            usuarioBd.setTelefono(usuario.getTelefono());
            usuarioRepository.save(usuarioBd);
            return usuarioBd;
        }catch (Exception e){
            return null;
        }
    }

    @DeleteMapping("/usuario/{id}")
    public Usuario eliminarUsuario(@PathVariable Long id){
        Usuario usuarioBd = usuarioRepository.findById(id).get();
        try {
            usuarioRepository.delete(usuarioBd);
            return usuarioBd;
        }catch (Exception e){
            return null;
        }
    }
}
