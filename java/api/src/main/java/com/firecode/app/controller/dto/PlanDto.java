package com.firecode.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.firecode.app.model.entity.PlanEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author fmatheus
 */
@JsonPropertyOrder({"id", "name", "price"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@Api(tags = "Plan", description = "API Plan")
public class PlanDto {

    @Getter
    @Setter
    @JsonProperty("id")
    @ApiModelProperty(notes = "Plan ID")
    private int id;

    @Getter
    @Setter
    @NotNull(message = "{name.not.null}")
    @NotBlank(message = "{name.not.blank}")
    @Size(min = 3, max = 45, message = "{name.size}")
    @JsonProperty("name")
    @ApiModelProperty(notes = "Name", name = "name", required = true)
    private String name;

    @Getter
    @Setter
    @NotNull(message = "{phone.not.null}")
    @NotBlank(message = "{phone.not.blank}")
    @JsonProperty("price")
    @ApiModelProperty(notes = "Price", name = "price", required = true)
    private BigDecimal price;

    public static PlanDto converterObject(PlanEntity entity) {

        var plan = new PlanDto();

        plan.setId(entity.getId());
        plan.setName(entity.getName());
        plan.setPrice(entity.getPrice());

        return plan;

    }

    public static PlanEntity converterEntity(PlanDto dto) {

        var plan = new PlanEntity();

        plan.setName(dto.getName());
        plan.setPrice(dto.getPrice());

        return plan;

    }

}
