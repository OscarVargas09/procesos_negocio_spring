package com.procesos.negocio.oscar.controllers;

import com.procesos.negocio.oscar.models.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UsuarioController {

    @GetMapping(value = "/usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id){
        Usuario usuario = new Usuario();
        usuario.setNombre("Oscar");
        usuario.setApellidos("Vargas");
        usuario.setFechaNacimiento(new Date(2021,07,9));
        usuario.setDocumento("123456");
        usuario.setTelefono("3154366645");
        usuario.setDireccion("calle A");
        return usuario;
    }
}
