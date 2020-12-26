package com.firecode.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.firecode.app.model.entity.PersonEntity;
import com.firecode.app.model.entity.PersonTypeEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author fmatheus
 */
@JsonPropertyOrder({"id", "id_type", "type", "name", "document"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@Api(tags = "Person", description = "API Person")
public class PersonDto {

    @Getter
    @Setter
    @JsonProperty("id")
    @ApiModelProperty(notes = "Person ID")
    private int id;

    @Getter
    @Setter
    @NotNull(message = "{name.not.null}")
    @NotBlank(message = "{name.not.blank}")
    @Size(min = 3, max = 70, message = "{name.size}")
    @JsonProperty("name")
    @ApiModelProperty(notes = "Client name", name = "name", required = true)
    private String name;

    @Getter
    @Setter
    @NotNull(message = "{type.not.null}")
    @NotBlank(message = "{type.not.blank}")
    @JsonProperty("id_type")
    @ApiModelProperty(notes = "Person type id", name = "id_type", required = true)
    private int idType;

    @Getter
    @Setter
    @JsonProperty("type")
    @ApiModelProperty(notes = "Person type", name = "type")
    private String type;

    @Getter
    @Setter
    @NotNull(message = "{document.not.null}")
    @NotBlank(message = "{document.not.blank}")
    @Size(min = 11, max = 20, message = "{name.size}")
    @JsonProperty("document")
    @ApiModelProperty(notes = "Customer document", name = "document", required = true)
    private String document;

    public static PersonDto converterObject(PersonEntity entity) {

        var person = new PersonDto();

        person.setId(entity.getId());
        person.setName(entity.getName());
        person.setDocument(entity.getDocument());
        person.setIdType(entity.getIdPersonType().getId());
        person.setType(entity.getIdPersonType().getName());

        return person;

    }

    public static PersonEntity converterEntity(PersonDto dto) {

        var person = new PersonEntity();

        person.setId(dto.getId());
        person.setIdPersonType(new PersonTypeEntity(dto.getIdType()));
        person.setName(dto.getName());
        person.setDocument(dto.getDocument());
        person.setIdPersonType(new PersonTypeEntity(dto.getIdType()));

        return person;

    }

}
