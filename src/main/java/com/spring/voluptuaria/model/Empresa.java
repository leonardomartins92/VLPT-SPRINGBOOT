package com.spring.voluptuaria.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false,unique = true)
    private String cnpj;
    @NotNull
    private String nome;
    @NotNull
    private String telefone;
    @Column(nullable = false,unique = true)
    private String email;
    @NotNull
    private String logradouro;
    @NotNull
    private String numero;
    private String complemento;
    @NotNull
    private String uf;
    @NotNull
    private String cep;
    @NotNull
    private String localidade;
    @ManyToOne
    @JoinColumn(nullable = false)
    private TipoEmpresa tipo;
}
