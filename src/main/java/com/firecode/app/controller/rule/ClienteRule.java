package com.firecode.app.controller.rule;

import com.firecode.app.controller.dto.ClienteDto;

import com.firecode.app.controller.event.ResourceEvent;
import com.firecode.app.controller.handler.response.MessageResponse;
import com.firecode.app.controller.util.AppUtil;
import com.firecode.app.model.entity.ClienteEntity;
import com.firecode.app.model.entity.LogradouroEntity;
import com.firecode.app.model.entity.PessoaEntity;
import com.firecode.app.model.service.ClienteService;
import com.firecode.app.model.service.LogradouroService;
import com.firecode.app.model.service.PessoaService;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ClienteRule {

    @Autowired
    private MessageSource messageSource;

    /* Publicador de eventos de aplicacao */
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private LogradouroService logradouroService;

    private ClienteDto clienteDto;

    public ResponseEntity<?> create(ClienteDto dto, HttpServletResponse response) {
        String cpf = AppUtil.removeSpecialCharacters(dto.getCpfCnpj());
        PessoaEntity pessoa = pessoaService.findBycpfCnpj(cpf);
        if (pessoa != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.messageResponse(400, HttpStatus.BAD_REQUEST, "message.error.documento.exist", null));
        }

        clienteDto = new ClienteDto();
        String cep = AppUtil.removeSpecialCharacters(dto.getCep());
        LogradouroEntity logradouro = logradouroService.findByCep(cep);
        pessoa = clienteDto.create(dto, logradouro);
        pessoaService.create(pessoa);
        publisher.publishEvent(new ResourceEvent(this, response, pessoa.getClienteEntity().getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.messageResponse(201, HttpStatus.CREATED, "message.success.create", null));
    }

    public ResponseEntity<Page<ClienteDto>> findAllPaginator(Pageable pageable) {
        Page<ClienteDto> clientes = clienteService.findAllPaginator(pageable).map(ClienteDto::converterObject);
        return !clientes.isEmpty() ? ResponseEntity.ok(clientes) : ResponseEntity.status(204).build();
    }

    public ResponseEntity<?> findById(int id) {
        clienteDto = new ClienteDto();
        ClienteDto cliente = clienteDto.find(clienteService.findById(id));
        return cliente != null ? ResponseEntity.ok(cliente) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.messageResponse(404, HttpStatus.NOT_FOUND, "message.error.not.found", null));
    }

    public ResponseEntity<?> delete(int id) {

        ClienteEntity cliente = clienteService.findById(id);
        if (cliente == null) {
            throw this.responseStatusException(HttpStatus.NOT_FOUND, "message.error.not.found");
        }
        pessoaService.deleteById(cliente.getIdPessoa().getId());
        return ResponseEntity.status(HttpStatus.OK).body(this.messageResponse(200, HttpStatus.OK, "message.success.delete", null));
    }

    public ResponseEntity<?> update(int id, ClienteDto dto, HttpServletResponse response) {

        ClienteEntity cliente = clienteService.findById(id);
        if (cliente == null) {
            throw this.responseStatusException(HttpStatus.NOT_FOUND, "message.error.not.found");
        }

        LogradouroEntity logradouro = null;
        String cep = AppUtil.removeSpecialCharacters(dto.getCep());

        // Se o cep informado for diferente que o cep que ja existe, realiza um consulta do novo sep 
        if (!cep.equals(cliente.getIdPessoa().getEnderecoEntity().getIdLogradouro().getCep())) {
            logradouro = logradouroService.findByCep(cep);
        } else {
            logradouro = cliente.getIdPessoa().getEnderecoEntity().getIdLogradouro();
        }

        clienteDto = new ClienteDto();
        PessoaEntity pessoa = clienteDto.update(cliente.getIdPessoa(), dto, logradouro);
        pessoaService.create(pessoa);
        publisher.publishEvent(new ResourceEvent(this, response, null));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.messageResponse(201, HttpStatus.CREATED, "message.success.update", null));

    }

    private MessageResponse messageResponse(int statusCode, HttpStatus statusDescription, String messageProperties, String cause) {
        String message = messageSource.getMessage(messageProperties, null, LocaleContextHolder.getLocale());
        return new MessageResponse(statusCode, statusDescription.name(), message, cause);
    }

    private ResponseStatusException responseStatusException(HttpStatus status, String messageProperties) {
        String message = messageSource.getMessage(messageProperties, null, LocaleContextHolder.getLocale());
        return new ResponseStatusException(status, message);
    }

}
