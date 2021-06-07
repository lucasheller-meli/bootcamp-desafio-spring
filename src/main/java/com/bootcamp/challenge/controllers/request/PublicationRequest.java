package com.bootcamp.challenge.controllers.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class PublicationRequest {

    @NotNull(message = "O id de quem está criando é obrigatorio.")
    @ApiModelProperty(value = "User do criador da publicacao")
    private Integer userId;

    @NotNull(message = "O id do produto que a publicacao está sendo criada é obrigatorio.")
    @ApiModelProperty(value = "Id do produto")
    private Integer productId;

    @NotNull(message = "O valor da publicacao é obrigatoria.")
    @ApiModelProperty(value = "Preco da publicacao")
    private BigDecimal price;

    @NotNull(message = "A categoria da publicacao é obrigatoria.")
    @ApiModelProperty(value = "Categoria da publicacao")
    private Integer category;

    @NotNull(message = "O campo de promocao é obrigatorio.")
    @ApiModelProperty(value = "Categoria da publicacao")
    private BigDecimal discount;

    @NotNull(message = "O campo de promocao é obrigatorio.")
    @ApiModelProperty(value = "Se tem desconto na publicacao")
    private Boolean hasPromo;
}
