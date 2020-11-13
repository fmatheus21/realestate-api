package com.firecode.app.controller.resource;

import com.firecode.app.controller.dto.ClienteDto;
import com.firecode.app.controller.rule.ClienteRule;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping()
    public ResponseEntity<Iterable<ClienteDto>> list() {
        return clienteRule.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> get(@PathVariable int id) {
        return clienteRule.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
        return null;
    }

    @PostMapping
    public ResponseEntity<ClienteDto> post(@RequestBody @Valid ClienteDto dto, HttpServletResponse response) {
        return clienteRule.create(dto, response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        return null;
    }

}
