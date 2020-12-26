package com.firecode.app.model.repository.query;

import com.firecode.app.model.entity.ClientEntity;
import com.firecode.app.model.repository.filter.RepositoryFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientRepositoryQuery {

    Page<ClientEntity> findAllPaginator(RepositoryFilter filter, Pageable pageable);

}
