package com.firecode.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.firecode.app.controller.util.LocalDatetUtil;
import com.firecode.app.model.entity.ClienteEntity;
import com.firecode.app.model.entity.EnderecoEntity;
import com.firecode.app.model.entity.LogradouroEntity;
import com.firecode.app.model.entity.PessoaEntity;
import com.firecode.app.model.entity.PessoaTipoEntity;
import com.firecode.app.model.entity.UsuarioEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({
    "id", "nome_razaosocial", "cpf_cnpj", "tipo", "logradouro", "numero", "complemento",
    "bairro", "cidade", "uf", "cep", "cadastrado_por", "data_cadastro", "alterado_por", "data_alteracao"})
@JsonInclude(Include.NON_NULL)
@Api(tags = "Clientes", description = "API de clientes")
public class ClienteDto {

    @Getter
    @Setter
    @JsonProperty("id")
    @ApiModelProperty(notes = "ID do cliente")
    private int id;
    @Getter
    @Setter
    @NotNull(message = "{name.not.null}")
    @NotBlank(message = "{name.not.blank}")
    @Size(min = 3, max = 70, message = "{name.size}")
    @JsonProperty("nome_razaosocial")
    @ApiModelProperty(notes = "Nome ou Razão Social do cliente", name = "nome_razaosocial", required = true)
    private String nomeRazaosocial;
    @Getter
    @Setter
    @NotNull(message = "{cpf.not.null}")
    @NotBlank(message = "{cpf.not.blank}")
    @JsonProperty("cpf_cnpj")
    @ApiModelProperty(notes = "CPF ou CNPJ do cliente", name = "cpf_cnpj", required = true)
    private String cpfCnpj;
    @Getter
    @Setter
    @NotNull(message = "{tipo.not.null}")
    @JsonProperty("id_tipo")
    @ApiModelProperty(notes = "ID do tipo de pessoa (física ou jurídica)", name = "tipo", required = true)
    private Integer idPessoaTipo;
    @Getter
    @Setter
    @NotNull(message = "{usuario.not.null}")
    @JsonProperty("id_usuario")
    @ApiModelProperty(notes = "ID do usuário que cadastrou o cliente", name = "id_usuario", required = true)
    private Integer idUsuario;
    @Getter
    @Setter
    @JsonProperty("tipo")
    private String pessoaTipo;
    @Getter
    @Setter
    @JsonProperty("cadastrado_por")
    private String usuarioCadastro;
    @Getter
    @Setter
    @JsonProperty("data_cadastro")
    private LocalDateTime dataCadastro;
    @Getter
    @Setter
    @JsonProperty("alterado_por")
    private String usuarioAlteracao;
    @Getter
    @Setter
    @JsonProperty("data_alteracao")
    private LocalDateTime dataAlteracao;

    /* Endereco */
    @Getter
    @Setter
    @NotNull(message = "{cep.not.null}")
    @NotBlank(message = "{cep.not.blank}")
    @Size(min = 8, max = 15, message = "{cep.size}")
    @JsonProperty("cep")
    @ApiModelProperty(notes = "Cep do cliente", name = "cep", required = true)
    private String cep;
    @Getter
    @Setter
    @NotNull(message = "{logradouro.not.null}")
    @NotBlank(message = "{logradouro.not.blank}")
    @Size(min = 3, max = 100, message = "{logradouro.size}")
    @JsonProperty("logradouro")
    @ApiModelProperty(notes = "Logradouro do cliente", name = "logradouro", required = true)
    private String logradouro;
    @Getter
    @Setter
    @NotNull(message = "{numero.not.null}")
    @NotBlank(message = "{numero.not.blank}")
    @Size(min = 1, max = 20, message = "{numero.size}")
    @JsonProperty("numero")
    @ApiModelProperty(notes = "Número da residência do cliente", name = "numero", required = true)
    private String numero;
    @Getter
    @Setter
    @Size(max = 50, message = "{complemento.size}")
    @JsonProperty("complemento")
    @ApiModelProperty(notes = "Complemendo do endereço do cliente", name = "complemento", required = false)
    private String complemento;
    @Getter
    @Setter
    @NotNull(message = "{bairro.not.null}")
    @NotBlank(message = "{bairro.not.blank}")
    @Size(min = 3, max = 100, message = "{bairro.size}")
    @JsonProperty("bairro")
    @ApiModelProperty(notes = "Bairro do cliente", name = "bairro", required = true)
    private String bairro;
    @Getter
    @Setter
    @NotNull(message = "{cidade.not.null}")
    @NotBlank(message = "{cidade.not.blank}")
    @JsonProperty("cidade")
    @ApiModelProperty(notes = "Cidade do cliente", name = "cidade", required = true)
    private String cidade;
    @Getter
    @Setter
    @NotNull(message = "{uf.not.null}")
    @NotBlank(message = "{uf.not.blank}")
    @Size(min = 2, max = 2, message = "{uf.size}")
    @ApiModelProperty(notes = "UF do cliente", name = "uf", required = true)
    @JsonProperty("uf")
    private String uf;

    public PessoaEntity create(ClienteDto dto, LogradouroEntity logradouro) {

        PessoaEntity pessoa = new PessoaEntity();
        ClienteEntity cliente = new ClienteEntity();
        EnderecoEntity endereco = new EnderecoEntity();

        pessoa.setNomeRazaosocial(dto.getNomeRazaosocial());
        pessoa.setCpfCnpj(dto.getCpfCnpj());
        pessoa.setIdTipo(new PessoaTipoEntity(dto.getIdPessoaTipo()));

        cliente.setIdPessoa(pessoa);
        cliente.setDataCadastro(LocalDatetUtil.currentDateTime());
        cliente.setDataAlteracao(LocalDatetUtil.currentDateTime());
        cliente.setIdUsuarioCadastro(new UsuarioEntity(dto.getIdUsuario()));
        cliente.setIdUsuarioAtualizacao(new UsuarioEntity(dto.getIdUsuario()));
        pessoa.setClienteEntity(cliente);

        endereco.setIdPessoa(pessoa);
        endereco.setNumero(dto.getNumero());
        endereco.setComplemento(dto.getComplemento());
        endereco.setIdLogradouro(logradouro);
        pessoa.setEnderecoEntity(endereco);

        return pessoa;
    }

    public PessoaEntity update(PessoaEntity pessoa, ClienteDto dto, LogradouroEntity logradouro) {

        pessoa.setNomeRazaosocial(dto.getNomeRazaosocial());
        pessoa.setCpfCnpj(dto.getCpfCnpj());
        pessoa.setIdTipo(new PessoaTipoEntity(dto.getIdPessoaTipo()));

        pessoa.getClienteEntity().setDataCadastro(LocalDatetUtil.currentDateTime());
        pessoa.getClienteEntity().setDataAlteracao(LocalDatetUtil.currentDateTime());
        pessoa.getClienteEntity().setIdUsuarioCadastro(new UsuarioEntity(dto.getIdUsuario()));
        pessoa.getClienteEntity().setIdUsuarioAtualizacao(new UsuarioEntity(dto.getIdUsuario()));

        pessoa.getEnderecoEntity().setIdPessoa(pessoa);
        pessoa.getEnderecoEntity().setNumero(dto.getNumero());
        pessoa.getEnderecoEntity().setComplemento(dto.getComplemento());
        pessoa.getEnderecoEntity().setIdLogradouro(logradouro);

        return pessoa;
    }

    public ClienteDto find(ClienteEntity cliente) {
        if (cliente == null) {
            return null;
        }
        return this.converterObject(cliente);
    }

    public static ClienteDto converterObject(ClienteEntity cliente) {

        ClienteDto dto = new ClienteDto();

        dto.setId(cliente.getId());
        dto.setNomeRazaosocial(cliente.getIdPessoa().getNomeRazaosocial());
        dto.setCpfCnpj(cliente.getIdPessoa().getCpfCnpj());
        dto.setPessoaTipo(cliente.getIdPessoa().getIdTipo().getNome());

        dto.setLogradouro(cliente.getIdPessoa().getEnderecoEntity().getIdLogradouro().getDescricaoSemNumero());
        dto.setNumero(cliente.getIdPessoa().getEnderecoEntity().getNumero());
        dto.setComplemento(cliente.getIdPessoa().getEnderecoEntity().getComplemento());
        dto.setBairro(cliente.getIdPessoa().getEnderecoEntity().getIdLogradouro().getDescricaoBairro());
        dto.setCidade(cliente.getIdPessoa().getEnderecoEntity().getIdLogradouro().getIdCidade().getDescricao());
        dto.setUf(cliente.getIdPessoa().getEnderecoEntity().getIdLogradouro().getIdCidade().getUf());
        dto.setCep(cliente.getIdPessoa().getEnderecoEntity().getIdLogradouro().getCep());

        dto.setUsuarioCadastro(cliente.getIdUsuarioCadastro().getIdPessoa().getNomeRazaosocial());
        dto.setDataCadastro(cliente.getDataCadastro());
        dto.setUsuarioAlteracao(cliente.getIdUsuarioAtualizacao().getIdPessoa().getNomeRazaosocial());
        dto.setDataAlteracao(cliente.getDataAlteracao());

        return dto;
    }

}
