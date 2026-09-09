package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PersonaRequest(
        @NotBlank(message = "El nombre no puede estar vacío") String nombre,
        @NotNull @Min(value = 18) Integer edad) {

}
