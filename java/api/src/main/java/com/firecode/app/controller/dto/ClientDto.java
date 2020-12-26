package com.firecode.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.firecode.app.controller.util.AppUtil;
import com.firecode.app.controller.util.LocalDatetUtil;
import com.firecode.app.model.entity.ClientEntity;
import com.firecode.app.model.entity.AddressEntity;
import com.firecode.app.model.entity.ContactEntity;
import com.firecode.app.model.entity.StreetEntity;
import com.firecode.app.model.entity.PersonEntity;
import com.firecode.app.model.entity.PlanEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

@JsonPropertyOrder({"id", "view", "delete", "created_at", "updated_at", "person", "address", "contact", "plan"})
@JsonInclude(Include.NON_NULL)
@Api(tags = "Client", description = "API Client")
public class ClientDto {

    @Getter
    @Setter
    @JsonProperty("id")
    @ApiModelProperty(notes = "Customer ID")
    private int id;

    @Getter
    @Setter
    @JsonProperty("delete")
    @ApiModelProperty(notes = "URL for deletion")
    private String delete;

    @Getter
    @Setter
    @JsonProperty("view")
    @ApiModelProperty(notes = "URL for view")
    private String view;

    @Getter
    @Setter
    @JsonProperty("filter")
    private String filter;

    @Getter
    @Setter
    @JsonProperty("created_at")
    @ApiModelProperty(notes = "Created At", name = "created_at")
    private LocalDateTime createdAt;

    @Getter
    @Setter
    @JsonProperty("updated_at")
    @ApiModelProperty(notes = "Updated At", name = "updated_at")
    private LocalDateTime updatedAt;

    @Getter
    @Setter
    @NotNull(message = "{person.not.null}")
    @JsonProperty("person")
    @ApiModelProperty(notes = "Person address", name = "person", required = true)
    private PersonDto personDto;

    @Getter
    @Setter
    @NotNull(message = "{address.not.null}")
    @JsonProperty("address")
    @ApiModelProperty(notes = "Customer address", name = "address", required = true)
    private AddressDto addressDto;

    @Getter
    @Setter
    @NotNull(message = "{contact.not.null}")
    @JsonProperty("contact")
    @ApiModelProperty(notes = "Customer contact", name = "contact", required = true)
    private ContactDto contactDto;

    @Getter
    @Setter
    @NotNull(message = "{plan.not.null}")
    @JsonProperty("plan")
    @ApiModelProperty(notes = "Customer plan", name = "plan", required = true)
    private PlanDto planDto;

    public PersonEntity create(ClientDto dto, StreetEntity street) {

        var person = new PersonEntity();
        var client = new ClientEntity();
        var address = new AddressEntity();
        var contact = new ContactEntity();

        person = PersonDto.converterEntity(dto.getPersonDto());

        client.setIdPerson(person);
        client.setIdPlan(new PlanEntity(dto.getPlanDto().getId()));
        client.setCreatedAt(LocalDatetUtil.currentDateTime());
        client.setUpdatedAt(LocalDatetUtil.currentDateTime());
        client.setFilter(
                this.converterJson(
                        dto.getPersonDto().getName(),
                        dto.getPersonDto().getDocument(),
                        street.getDescriptionWithoutNumber(),
                        street.getDescriptionDistrict(),
                        street.getIdCity().getDescription(),
                        street.getIdCity().getState(),
                        street.getZipCode()
                ));
        client.setIdPerson(person);
        person.setClientEntity(client);

        address.setComplement(dto.getAddressDto().getComplement());
        address.setNumber(dto.getAddressDto().getNumber());
        address.setIdStreet(street);
        address.setIdPerson(person);
        person.setAddressEntity(address);

        contact.setEmail(dto.getContactDto().getEmail());
        contact.setPhone(dto.getContactDto().getPhone());
        contact.setIdPerson(person);
        person.setContactEntity(contact);

        /*   person.setName(dto.getPersonDto().getName());
        person.setDocument(dto.getPersonDto().getDocument());
        person.setIdPersonType(new PersonTypeEntity(dto.getPersonDto().getIdType()));

        client.setIdPerson(person);
        client.setCreatedAt(LocalDatetUtil.currentDateTime());
        client.setUpdatedAt(LocalDatetUtil.currentDateTime());

        client.setFilter(
                this.converterJson(
                        dto.getPersonDto().getName(),
                        dto.getPersonDto().getDocument(),
                        publicPlace.getDescriptionWithoutNumber(),
                        publicPlace.getDescriptionDistrict(),
                        publicPlace.getIdCity().getDescription(),
                        publicPlace.getIdCity().getState(),
                        publicPlace.getZipCode()
                ));
        person.setClientEntity(client);

        address.setIdPerson(person);
        address.setNumber(dto.getAddressDto().getNumber());
        address.setComplement(dto.getAddressDto().getComplement());
        address.setIdPublicPlace(publicPlace);
        person.setAddressEntity(address);*/
        return person;
    }

    public PersonEntity update(PersonEntity person, ClientDto dto, StreetEntity street) {

        /*person.setName(dto.getPersonDto().getName());
        person.setDocument(dto.getPersonDto().getDocument());
        person.setIdPersonType(new PersonTypeEntity(dto.getPersonDto().getIdType()));*/
        person = PersonDto.converterEntity(dto.getPersonDto());

        person.getClientEntity().setCreatedAt(LocalDatetUtil.currentDateTime());
        person.getClientEntity().setUpdatedAt(LocalDatetUtil.currentDateTime());
        person.getClientEntity().setFilter(
                this.converterJson(
                        person.getName(),
                        person.getDocument(),
                        person.getAddressEntity().getIdStreet().getDescriptionWithoutNumber(),
                        person.getAddressEntity().getIdStreet().getDescriptionDistrict(),
                        person.getAddressEntity().getIdStreet().getIdCity().getDescription(),
                        person.getAddressEntity().getIdStreet().getIdCity().getState(),
                        person.getAddressEntity().getIdStreet().getZipCode()
                ));

        person.getAddressEntity().setIdPerson(person);
        person.getAddressEntity().setNumber(dto.getAddressDto().getNumber());
        person.getAddressEntity().setComplement(dto.getAddressDto().getComplement());
        person.getAddressEntity().setIdStreet(street);

        return person;
    }

    public static ClientDto converterObject(ClientEntity client) {

        if (client == null) {
            return null;
        }

        var dto = new ClientDto();

        dto.setId(client.getId());
        dto.setDelete("/api/clients/" + client.getId());
        dto.setView("/api/clients/" + client.getId());
        dto.setPersonDto(PersonDto.converterObject(client.getIdPerson()));
        dto.setAddressDto(AddressDto.converterObject(client.getIdPerson().getAddressEntity()));
        dto.setContactDto(ContactDto.converterObject(client.getIdPerson().getContactEntity()));
        dto.setPlanDto(PlanDto.converterObject(client.getIdPlan()));
        dto.setCreatedAt(client.getCreatedAt());
        dto.setUpdatedAt(client.getUpdatedAt());

        return dto;

    }

    private String converterJson(String name, String document, String publicPlace, String district, String city, String state, String zipCode) {
        JSONObject obj = new JSONObject();
        obj.put("name", AppUtil.convertAllLowercaseCharacters(name));
        obj.put("document", AppUtil.removeSpecialCharacters(document));
        obj.put("street", AppUtil.convertAllLowercaseCharacters(publicPlace));
        obj.put("district", AppUtil.convertAllLowercaseCharacters(district));
        obj.put("city", AppUtil.convertAllLowercaseCharacters(city));
        obj.put("state", AppUtil.convertAllLowercaseCharacters(state));
        obj.put("zip_code", AppUtil.removeSpecialCharacters(zipCode));
        return obj.toString();
    }
}
