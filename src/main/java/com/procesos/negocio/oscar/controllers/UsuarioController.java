package com.procesos.negocio.oscar.controllers;

import com.procesos.negocio.oscar.models.Usuario;
import com.procesos.negocio.oscar.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "/usuario/{id}")
    public ResponseEntity getUsuario(@PathVariable Long id){
       Optional<Usuario> usuario= usuarioRepository.findById(id);
       if(usuario.isPresent()){
           return new ResponseEntity(usuario, HttpStatus.OK);
       }
       return ResponseEntity.notFound().build();

    }

    @PostMapping("/usuario")
    public ResponseEntity crearUsuario(@RequestBody Usuario usuario){
        try{
            usuarioRepository.save(usuario);
            return new ResponseEntity(usuario, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping(value = "/usuarios")
    public ResponseEntity listarUsuarios(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        if(usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios, HttpStatus.OK);
    }

    @GetMapping("/usuario/{nombre}/{apellidos}")
    public ResponseEntity listarPorNombreApellidos(@PathVariable String nombre,@PathVariable String apellidos){

        List<Usuario> usuarios = usuarioRepository.findAllByNombreAndApellidos(nombre, apellidos);
        if(usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }

    @GetMapping("/usuario/nombre/{nombre}")
    public ResponseEntity listarPorNombre(@PathVariable String nombre){
        List<Usuario> usuarios = usuarioRepository.findAllByNombre(nombre);
        if(usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }

    @GetMapping("/usuario/apellido/{apellidos}")
    public ResponseEntity listarPorApellido(@PathVariable String apellidos){
        List<Usuario> usuarios = usuarioRepository.findAllByApellidos(apellidos);
        if(usuarios.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(usuarios,HttpStatus.OK);
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity editarUsuario(@PathVariable Long id,@RequestBody Usuario usuario){
        Optional<Usuario> usuarioBd= usuarioRepository.findById(id);
        if(usuarioBd.isPresent()){
            try {
                usuarioBd.get().setNombre(usuario.getNombre());
                usuarioBd.get().setApellidos(usuario.getApellidos());
                usuarioBd.get().setDireccion(usuario.getDireccion());
                usuarioBd.get().setDocumento(usuario.getDocumento());
                usuarioBd.get().setFechaNacimiento(usuario.getFechaNacimiento());
                usuarioBd.get().setTelefono(usuario.getTelefono());
                usuarioRepository.save(usuarioBd.get());
                return new ResponseEntity(usuarioBd, HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        Optional<Usuario> usuarioBd = usuarioRepository.findById(id);
        if(usuarioBd.isPresent()){
            usuarioRepository.delete(usuarioBd.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
