package com.firecode.app.controller.resource;

import com.firecode.app.controller.dto.ClientDto;
import com.firecode.app.controller.rule.ClientRule;
import com.firecode.app.model.repository.filter.RepositoryFilter;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/clients")
public class ClientResource {

    @Autowired
    private ClientRule clientRule;

    @ApiOperation(value = "List customers")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns a list"),
        @ApiResponse(code = 204, message = "No content"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Server error"),})
    @PreAuthorize("hasAnyAuthority('ADMIN', 'GESTOR') and #oauth2.hasScope('read')")
    @GetMapping()
    public ResponseEntity<Page<ClientDto>> list(RepositoryFilter filter, Pageable page) {
        return clientRule.findAllPaginator(filter, page);
    }

    @ApiOperation(value = "Consult customer by ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Returns the record"),
        @ApiResponse(code = 404, message = "Register not found"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Server error"),})
    @PreAuthorize("hasAnyAuthority('ADMIN', 'GESTOR') and #oauth2.hasScope('read')")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable int id) {
        return clientRule.findById(id);
    }

    @ApiOperation(value = "Update client data by ID")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Record created"),
        @ApiResponse(code = 404, message = "Register not found"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Server error"),})
    @PreAuthorize("hasAnyAuthority('ADMIN', 'GESTOR') and #oauth2.hasScope('write')")
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable int id, @RequestBody ClientDto clientDto, HttpServletResponse response) {
        return clientRule.update(id, clientDto, response);
    }

    @ApiOperation(value = "Register a new customer")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Record created"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Server error"),})
    @PreAuthorize("hasAnyAuthority('ADMIN', 'GESTOR') and #oauth2.hasScope('write')")
    @PostMapping
    public ResponseEntity<?> post(@RequestBody @Valid ClientDto dto, HttpServletResponse response) {
        return clientRule.create(dto, response);
    }

    @ApiOperation(value = "Delete a client by ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Record deleted"),
        @ApiResponse(code = 404, message = "Register not found"),
        @ApiResponse(code = 403, message = "Access denied"),
        @ApiResponse(code = 500, message = "Server error"),})
    @PreAuthorize("hasAnyAuthority('ADMIN') and #oauth2.hasScope('write')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return clientRule.delete(id);
    }

}
