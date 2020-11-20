package com.firecode.app.model.repository;

import com.firecode.app.model.entity.UsuarioEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    public Optional<UsuarioEntity> findByUsuario(String value);

}
