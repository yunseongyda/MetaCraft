package com.metacraft.assetstore.Entities.Form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductForm {
  @Size(max = 100, message = "The subject must be less than 100 characters.")
  @NotBlank(message = "The subject is necessary.")
  private String subject;
  @NotBlank(message = "The introduction is necessary.")
  @Size(max = 1000, message = "The introduction must be less than 1000 characters.")
  private String introduction;
  @NotBlank(message = "The description is necessary.")
  @Size(max = 10000, message = "The description must be less than 10000 characters.")
  private String description;

  private String category;
  
  private int price;
}
