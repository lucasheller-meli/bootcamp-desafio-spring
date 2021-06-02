package com.bootcamp.challenge.controllers.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ProductRequest {

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 120, message = "O tamanho máximo do nome do produto é de {max}")
    @ApiModelProperty(value = "Nome do produto")
    private String name;

    @NotBlank(message = "O tipo de produto é obrigatório.")
    @Size(max = 55, message = "O tamanho máximo do tipo do produto é de {max}")
    @ApiModelProperty(value = "Tipo do produto")
    private String type;

    @NotBlank(message = "Marca do produto é obrigatória.")
    @Size(max = 30, message = "O tamanho máximo da marca é de {max}")
    @ApiModelProperty(value = "Marca do produto")
    private String brand;

    @NotBlank(message = "A cor é obrigatória.")
    @Size(max = 30, message = "O tamanho máximo da cor é de {max}")
    @ApiModelProperty(value = "Cor do produto")
    private String color;

    @NotBlank(message = "A descricao é obrigatória.")
    @Size(max = 150, message = "A descricao máxima do produto é de {max}")
    @ApiModelProperty(value = "Descricao do produto")
    private String notes;
}
