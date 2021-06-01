package com.bootcamp.challenge.controllers.request;

import com.bootcamp.challenge.entities.UserType;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserCreateRequest {

    @NotBlank(message = "O nome é obrigatório.")
    @Size(max = 120, message = "O tamanho máximo do nome é de {max}")
    @ApiModelProperty(value = "Nome do cliente")
    private String name;

    @NotNull(message = "O tipo de usuário é obrigatório")
    @ApiModelProperty(value = "Tipo do usuário")
    //TODO @Pattern(regexp = "BUYER|SELLER", message = "Tipo informado inválido")
    private UserType type;


}
