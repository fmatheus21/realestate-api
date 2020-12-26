package com.firecode.app.model.repository;

import com.firecode.app.model.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.firecode.app.model.repository.query.ClientRepositoryQuery;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Integer>, ClientRepositoryQuery {

    /*@Query("FROM Customer c " + "WHERE LOWER(c.name) like %:searchTerm% " + "OR LOWER(c.email) like %:searchTerm%")
    @Query("FROM ClienteEntity c ")
    Page<ClienteEntity> findAllPaginator(Pageable pageable);*/
}
