package br.iesb.poo.restapi.dto;
import jakarta.validation.constraints.*;

public record UpdateCustomerRequest(
        @NotBlank
        @Size(min = 2, max = 1000)
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Size(min = 6, max = 50)
        String password,
        @NotNull
        @Min(18)
        Integer age,
        @Pattern(regexp = "^(Masculino|Feminino|Outro)$")
        String gender
) {}
