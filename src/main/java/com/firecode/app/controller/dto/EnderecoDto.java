package com.firecode.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

public class EnderecoDto {

    @Getter
    @Setter
    @NotNull
    @NotBlank
    @JsonProperty("cep")
    private String cep;
    @Getter
    @Setter
    @JsonProperty("logradouro")
    private String logradouro;
    @Getter
    @Setter
    @JsonProperty("numero")
    private String numero;
    @Getter
    @Setter
    @JsonProperty("complemento")
    private String complemento;
    @Getter
    @Setter
    @JsonProperty("bairro")
    private String bairro;
    @Getter
    @Setter
    @JsonProperty("cidade")
    private String cidade;
    @Getter
    @Setter
    @JsonProperty("uf")
    private String uf;

}
