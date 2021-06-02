package com.bootcamp.challenge.controllers.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class PublicationRequest {

    @NotNull(message = "O id de quem está criando é obrigatorio.")
    private Integer userId;

    @NotNull(message = "O id do produto que a publicacao está sendo criada é obrigatorio.")
    private Integer productId;

    @NotNull(message = "O valor da publicacao é obrigatoria.")
    private BigDecimal price;

    @NotNull(message = "A categoria da publicacao é obrigatoria.")
    private Integer category;
}
