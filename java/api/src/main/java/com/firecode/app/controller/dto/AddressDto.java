package com.firecode.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.firecode.app.model.entity.AddressEntity;
import com.firecode.app.model.entity.PersonEntity;
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
@JsonPropertyOrder({"id","street", "number", "complement", "district", "city", "state", "zip_code"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@Api(tags = "Address", description = "API Address")
public class AddressDto {

    @Getter
    @Setter
    @JsonProperty("id")
    @ApiModelProperty(notes = "Address ID")
    private int id;

    @Getter
    @Setter   
    private int idPerson;

    @Getter
    @Setter
    @NotNull(message = "{zipcode.not.null}")
    @NotBlank(message = "{zipcode.not.blank}")
    @Size(min = 8, max = 15, message = "{zipcode.size}")
    @JsonProperty("zip_code")
    @ApiModelProperty(notes = "Zip Code", name = "zip_code", required = true)
    private String zipCode;

    @Getter
    @Setter
    @NotNull(message = "{street.not.null}")
    @NotBlank(message = "{street.not.blank}")
    @Size(min = 3, max = 100, message = "{street.size}")
    @JsonProperty("street")
    @ApiModelProperty(notes = "Street", name = "street", required = true)
    private String street;

    @Getter
    @Setter
    @NotNull(message = "{number.not.null}")
    @NotBlank(message = "{number.not.blank}")
    @Size(min = 1, max = 20, message = "{number.size}")
    @JsonProperty("number")
    @ApiModelProperty(notes = "House number", name = "number", required = true)
    private String number;

    @Getter
    @Setter
    @Size(max = 50, message = "{complement.size}")
    @JsonProperty("complement")
    @ApiModelProperty(notes = "Address complement", name = "complement", required = false)
    private String complement;

    @Getter
    @Setter
    @NotNull(message = "{district.not.null}")
    @NotBlank(message = "{district.not.blank}")
    @Size(min = 3, max = 70, message = "{district.size}")
    @JsonProperty("district")
    @ApiModelProperty(notes = "District", name = "district", required = true)
    private String district;

    @Getter
    @Setter
    @NotNull(message = "{city.not.null}")
    @NotBlank(message = "{city.not.blank}")
    @JsonProperty("city")
    @ApiModelProperty(notes = "City", name = "city", required = true)
    private String city;

    @Getter
    @Setter
    @NotNull(message = "{state.not.null}")
    @NotBlank(message = "{state.not.blank}")
    @Size(min = 2, max = 2, message = "{state.size}")
    @ApiModelProperty(notes = "State", name = "state", required = true)
    @JsonProperty("state")
    private String state;

    public static AddressDto converterObject(AddressEntity entity) {

        var address = new AddressDto();

        address.setId(entity.getId());
        address.setIdPerson(entity.getIdPerson().getId());
        address.setDistrict(entity.getIdStreet().getDescriptionDistrict());
        address.setNumber(entity.getNumber());
        address.setComplement(entity.getComplement());
        address.setDistrict(entity.getIdStreet().getDescriptionDistrict());
        address.setCity(entity.getIdStreet().getIdCity().getDescription());
        address.setState(entity.getIdStreet().getIdCity().getState());
        address.setZipCode(entity.getIdStreet().getZipCode());

        return address;

    }
    


}
