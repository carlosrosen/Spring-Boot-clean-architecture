package br.iesb.poo.restapi.dto;

import java.util.UUID;

public record CustomerResponse (
    UUID id,
    String name,
    String email,
    Integer age,
    String gender
) {}
