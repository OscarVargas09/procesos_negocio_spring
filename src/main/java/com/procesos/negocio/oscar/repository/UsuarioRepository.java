package com.procesos.negocio.oscar.repository;

import com.procesos.negocio.oscar.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    List<Usuario> findAllByNombre(String nombre);

    List<Usuario> findAllByApellidos(String apellidos);

    List<Usuario> findAllByNombreAndApellidos(String nombre, String apellidos);
}
