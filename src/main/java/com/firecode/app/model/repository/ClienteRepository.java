package com.firecode.app.model.repository;

import com.firecode.app.model.entity.ClienteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

    //@Query("FROM Customer c " + "WHERE LOWER(c.name) like %:searchTerm% " + "OR LOWER(c.email) like %:searchTerm%")
    @Query("FROM ClienteEntity c ")
    Page<ClienteEntity> findAllPaginator(Pageable pageable);

}
