package br.iesb.poo.restapi.service;

import br.iesb.poo.restapi.dto.CustomerRequest;
import br.iesb.poo.restapi.dto.CustomerResponse;
import br.iesb.poo.restapi.dto.UpdateCustomerRequest;

import java.util.List;
import java.util.UUID;

public interface ICustomerService {
    List<CustomerResponse> findAll ();
    CustomerResponse findByID(UUID id);
    CustomerResponse create(CustomerRequest request);
    CustomerResponse update(UUID id, UpdateCustomerRequest request);
    void delete(UUID id);
}
