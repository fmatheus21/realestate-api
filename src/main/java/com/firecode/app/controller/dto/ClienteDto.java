package com.firecode.app.controller.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.firecode.app.controller.util.LocalDatetUtil;
import com.firecode.app.model.entity.ClienteEntity;
import com.firecode.app.model.entity.PessoaEntity;
import com.firecode.app.model.entity.PessoaTipoEntity;
import com.firecode.app.model.entity.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@JsonPropertyOrder({ "id", "nomeRazaosocial" })
@JsonInclude(Include.NON_NULL)
public class ClienteDto {

    @Getter
    @Setter
    @JsonProperty("id")
    private int id;
    @Getter
    @Setter
    @NotNull    
    @NotBlank
    @JsonProperty("nome_razaosocial")
    //@JsonGetter("nome_razaosocial")    
    @Size(min = 3, max = 70)
    private String nomeRazaosocial;
    @Getter 
    @Setter
    @NotNull
    @NotBlank
    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;
    @Getter
    @Setter
    @NotNull
    @JsonProperty("id_tipo")
    private Integer idPessoaTipo;
    @Getter
    @Setter
    @NotNull
    @JsonProperty("id_usuario")
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
    private String dataCadastro;
    @Getter
    @Setter
    @JsonProperty("alterado_por")
    private String usuarioAlteracao;
    @Getter
    @Setter
    @JsonProperty("data_alteracao")
    private String dataAlteracao;

    public PessoaEntity create(ClienteDto dto) {

        PessoaEntity pessoa = new PessoaEntity();
        ClienteEntity cliente = new ClienteEntity();

        pessoa.setNomeRazaosocial(dto.getNomeRazaosocial());
        pessoa.setCpfCnpj(dto.getCpfCnpj());
        pessoa.setIdTipo(new PessoaTipoEntity(dto.getIdPessoaTipo()));

        cliente.setIdPessoa(pessoa);
        cliente.setDataCadastro(LocalDatetUtil.currentDateTime());
        cliente.setDataAlteracao(LocalDatetUtil.currentDateTime());
        cliente.setIdUsuarioCadastro(new UsuarioEntity(dto.getIdUsuario()));
        cliente.setIdUsuarioAtualizacao(new UsuarioEntity(dto.getIdUsuario()));
        pessoa.setClienteEntity(cliente);

        return pessoa;
    }

    public List<ClienteDto> list(List<ClienteEntity> listClientes) {
        List<ClienteDto> list = new ArrayList<>();
        listClientes.stream().map(cliente -> {
            ClienteDto dto = new ClienteDto();
            dto.setId(cliente.getId());
            dto.setNomeRazaosocial(cliente.getIdPessoa().getNomeRazaosocial());
            dto.setCpfCnpj(cliente.getIdPessoa().getCpfCnpj());
            dto.setPessoaTipo(cliente.getIdPessoa().getIdTipo().getNome());
            dto.setUsuarioCadastro(cliente.getIdUsuarioCadastro().getIdPessoa().getNomeRazaosocial());
            dto.setDataCadastro(LocalDatetUtil.converterLocalDateTimeToString(cliente.getDataCadastro()));
            dto.setUsuarioAlteracao(cliente.getIdUsuarioAtualizacao().getIdPessoa().getNomeRazaosocial());
            dto.setDataAlteracao(LocalDatetUtil.converterLocalDateTimeToString(cliente.getDataAlteracao()));

            /*dto.setLogradouro(cliente.getIdPessoa().getEnderecoEntity().getIdLogradouro().getDescricaoSemNumero());
            dto.setNumero(cliente.getIdPessoa().getEnderecoEntity().getNumero());
            dto.setComplemento(cliente.getIdPessoa().getEnderecoEntity().getComplemento());
            dto.setBairro(cliente.getIdPessoa().getEnderecoEntity().getIdLogradouro().getDescricaoBairro());
            dto.setCidade(cliente.getIdPessoa().getEnderecoEntity().getIdLogradouro().getIdCidade().getDescricao());
            dto.setUf(cliente.getIdPessoa().getEnderecoEntity().getIdLogradouro().getIdCidade().getUf());
            dto.setCep(cliente.getIdPessoa().getEnderecoEntity().getIdLogradouro().getCep());*/
            return dto;
        }).forEachOrdered(dto -> {
            list.add(dto);
        });
        return list;
    }

    public ClienteDto find(ClienteEntity cliente) {
        if (cliente == null) {
            return null;
        }
        ClienteDto dto = new ClienteDto();
        dto.setId(cliente.getId());
        dto.setNomeRazaosocial(cliente.getIdPessoa().getNomeRazaosocial());
        dto.setCpfCnpj(cliente.getIdPessoa().getCpfCnpj());
        dto.setPessoaTipo(cliente.getIdPessoa().getIdTipo().getNome());
        return dto;
    }

    public ClienteDto responseObject(PessoaEntity pessoa) {
        ClienteDto dto = new ClienteDto();
        dto.setId(pessoa.getClienteEntity().getId());
        dto.setNomeRazaosocial(pessoa.getClienteEntity().getIdPessoa().getNomeRazaosocial());
        dto.setCpfCnpj(pessoa.getClienteEntity().getIdPessoa().getCpfCnpj());
        dto.setPessoaTipo(pessoa.getClienteEntity().getIdPessoa().getIdTipo().getNome());
        return dto;
    }

}
