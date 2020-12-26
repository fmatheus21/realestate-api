package com.firecode.app.controller.rule;

import com.firecode.app.controller.dto.ClientDto;

import com.firecode.app.controller.event.ResourceEvent;
import com.firecode.app.controller.handler.response.MessageResponse;
import com.firecode.app.controller.util.AppUtil;
import com.firecode.app.model.entity.ClientEntity;
import com.firecode.app.model.entity.StreetEntity;
import com.firecode.app.model.entity.PersonEntity;
import com.firecode.app.model.repository.filter.RepositoryFilter;
import com.firecode.app.model.service.ClientService;
import com.firecode.app.model.service.StreetService;
import com.firecode.app.model.service.PersonService;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class ClientRule {

    private static final Logger log = LoggerFactory.getLogger(ClientRule.class);

    @Autowired
    private MessageSource messageSource;

    /* Publicador de eventos de aplicacao */
    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PersonService personService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private StreetService streetService;

    public ResponseEntity<?> create(ClientDto dto, HttpServletResponse response) {

        String document = AppUtil.removeSpecialCharacters(dto.getPersonDto().getDocument());
        PersonEntity person = personService.findByDocument(document);

        /* se o documento existire */
        if (person != null) {
            log.error("The informed document already exists");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(this.messageResponse(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, "message.error.documento.exist", null));
        }

        String zipCode = AppUtil.removeSpecialCharacters(dto.getAddressDto().getZipCode());
        var street = streetService.findByZipCode(zipCode);

        /* Se o cep nao for encofntrado */
        if (street == null) {
            log.error("Zip code not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.messageResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, "message.error.zipcode.not.found", null));
        }

        var clientDto = new ClientDto();
        person = clientDto.create(dto, street);
        personService.create(person);
        publisher.publishEvent(new ResourceEvent(this, response, person.getClientEntity().getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.messageResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED, "message.success.create", null));
    }

    public ResponseEntity<Page<ClientDto>> findAllPaginator(RepositoryFilter filter, Pageable pageable) {
        Page<ClientDto> clients = clientService.findAllPaginator(filter, pageable).map(ClientDto::converterObject);
        return !clients.isEmpty() ? ResponseEntity.ok(clients) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    public ResponseEntity<?> findById(int id) {
        ClientDto client = ClientDto.converterObject(clientService.findById(id));
        return client != null ? ResponseEntity.ok(client) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(this.messageResponse(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, "message.error.not.found", null));
    }

    public ResponseEntity<?> delete(int id) {

        ClientEntity client = clientService.findById(id);
        if (client == null) {
            throw this.responseStatusException(HttpStatus.NOT_FOUND, "message.error.not.found");
        }
        
        personService.deleteById(client.getIdPerson().getId());
        return ResponseEntity.status(HttpStatus.OK).body(this.messageResponse(HttpStatus.OK.value(), HttpStatus.OK, "message.success.delete", null));
    }

    public ResponseEntity<?> update(int id, ClientDto dto, HttpServletResponse response) {

        ClientEntity client = clientService.findById(id);
        if (client == null) {
            throw this.responseStatusException(HttpStatus.NOT_FOUND, "message.error.not.found");
        }

        StreetEntity street = null;
        String zipCode = AppUtil.removeSpecialCharacters(dto.getAddressDto().getZipCode());

        // Se o cep informado for diferente que o cep que ja existe, realiza um consulta do novo sep 
        if (!zipCode.equals(client.getIdPerson().getAddressEntity().getIdStreet().getZipCode())) {
            street = streetService.findByZipCode(zipCode);
        } else {
            street = client.getIdPerson().getAddressEntity().getIdStreet();
        }

        var clientDto = new ClientDto();
        PersonEntity person = clientDto.update(client.getIdPerson(), dto, street);
        personService.create(person);
        publisher.publishEvent(new ResourceEvent(this, response, null));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.messageResponse(HttpStatus.CREATED.value(), HttpStatus.CREATED, "message.success.update", null));

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
