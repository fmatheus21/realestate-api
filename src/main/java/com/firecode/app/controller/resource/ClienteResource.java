package com.firecode.app.controller.resource;

import com.firecode.app.controller.dto.ClienteDto;
import com.firecode.app.controller.rule.ClienteRule;
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
@RequestMapping("/clientes")
public class ClienteResource {

    @Autowired
    private ClienteRule clienteRule;

    @ApiOperation(value = "Lista os clientes")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna um lista"),
        @ApiResponse(code = 204, message = "Sem Conteúdo"),
        @ApiResponse(code = 403, message = "Acesso Negado"),
        @ApiResponse(code = 500, message = "Erro no servidor"),})
    @PreAuthorize("hasAnyAuthority('ADMIN', 'GESTOR') and #oauth2.hasScope('read')")
    @GetMapping()
    public ResponseEntity<Page<ClienteDto>> list(Pageable page) {
        return clienteRule.findAllPaginator(page);
    }

    @ApiOperation(value = "Consultar cliente por ID")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna o registro"),
        @ApiResponse(code = 404, message = "Registro não encontrado"),
        @ApiResponse(code = 403, message = "Acesso Negado"),
        @ApiResponse(code = 500, message = "Erro no servidor"),})
    @PreAuthorize("hasAnyAuthority('ADMIN', 'GESTOR') and #oauth2.hasScope('read')")
    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable int id) {
        return clienteRule.findById(id);
    }

    @ApiOperation(value = "Atualizar dados do cliente por ID")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Registro criado"),
        @ApiResponse(code = 404, message = "Registro não encontrado"),
        @ApiResponse(code = 403, message = "Acesso Negado"),
        @ApiResponse(code = 500, message = "Erro no servidor"),})
    @PreAuthorize("hasAnyAuthority('ADMIN', 'GESTOR') and #oauth2.hasScope('write')")
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable int id, @RequestBody ClienteDto clienteDto, HttpServletResponse response) {
        return clienteRule.update(id, clienteDto, response);
    }

    @ApiOperation(value = "Cadastrar um novo cliente")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Registro criado"),
        @ApiResponse(code = 403, message = "Acesso Negado"),
        @ApiResponse(code = 500, message = "Erro no servidor"),})
    @PreAuthorize("hasAnyAuthority('ADMIN', 'GESTOR') and #oauth2.hasScope('write')")
    @PostMapping
    public ResponseEntity<?> post(@RequestBody @Valid ClienteDto dto, HttpServletResponse response) {
        return clienteRule.create(dto, response);
    }

    @ApiOperation(value = "Excluir um cliente por ID") 
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Registro excluído"),
        @ApiResponse(code = 404, message = "Registro não encontrado"),
        @ApiResponse(code = 403, message = "Acesso Negado"),
        @ApiResponse(code = 500, message = "Erro no servidor"),})
    @PreAuthorize("hasAnyAuthority('ADMIN') and #oauth2.hasScope('write')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return clienteRule.delete(id);
    }

}
