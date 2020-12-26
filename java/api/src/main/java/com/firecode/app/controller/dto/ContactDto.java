package com.firecode.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.firecode.app.model.entity.ContactEntity;
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
@JsonPropertyOrder({"id", "email", "phone"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@Api(tags = "Contact", description = "API Address")
public class ContactDto {

    @Getter
    @Setter
    @JsonProperty("id")
    @ApiModelProperty(notes = "Contact ID")
    private int id;

    @Getter
    @Setter
    @NotNull(message = "{email.not.null}")
    @NotBlank(message = "{email.not.blank}")
    @Size(min = 8, max = 100, message = "{email.size}")
    @JsonProperty("email")
    @ApiModelProperty(notes = "Email", name = "email", required = true)
    private String email;

    @Getter
    @Setter
    @NotNull(message = "{phone.not.null}")
    @NotBlank(message = "{phone.not.blank}")
    @Size(min = 8, max = 20, message = "{phone.size}")
    @JsonProperty("phone")
    @ApiModelProperty(notes = "Phone", name = "phone", required = true)
    private String phone;

    public static ContactDto converterObject(ContactEntity entity) {

        var contact = new ContactDto();

        contact.setId(entity.getId());
        contact.setEmail(entity.getEmail());
        contact.setPhone(entity.getPhone());

        return contact;

    }

}
