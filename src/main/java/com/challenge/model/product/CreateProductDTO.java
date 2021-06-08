package com.challenge.model.product;

import com.challenge.enumx.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDTO implements Serializable {

    private String productName;

    private LocalDate date;

    private Type type;

    private String brand;

    private String color;

    private String notes;

}
