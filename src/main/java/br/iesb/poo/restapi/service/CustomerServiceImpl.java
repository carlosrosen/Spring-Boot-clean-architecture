package br.iesb.poo.restapi.service;

import br.iesb.poo.restapi.dto.CustomerRequest;
import br.iesb.poo.restapi.dto.CustomerResponse;
import br.iesb.poo.restapi.dto.UpdateCustomerRequest;
import br.iesb.poo.restapi.exception.CustomerNotFoundException;
import br.iesb.poo.restapi.model.Customer;
import br.iesb.poo.restapi.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService {

    public final CustomerRepository repository;

    public CustomerServiceImpl(CustomerRepository repository){
        this.repository = repository;
    }

    private CustomerResponse toResponse(Customer c){
        return new CustomerResponse(c.getId(), c.getName(), c.getEmail(), c.getAge(), c.getGender());
    }

    @Override
    public List<CustomerResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse).toList();
    }

    @Override
    public CustomerResponse findByID(UUID id) {
        Customer found = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        return toResponse(found);
    }

    @Override
    public CustomerResponse create(CustomerRequest request) {
        if(repository.existsByEmail(request.email()))
            throw new IllegalArgumentException("Email já existente!");
        Customer customer = new Customer();
        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setPassword(request.password());
        customer.setAge(request.age());
        customer.setGender(request.gender());
        Customer saved = repository.save(customer);
        return toResponse(saved);
    }

    @Override
    public CustomerResponse update(UUID id, UpdateCustomerRequest request) {
        Customer found = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        if(request.email() != null && repository.existsByEmail(request.email()))
            throw new IllegalArgumentException("Email já existente!");
        if(request.name() != null) found.setName(request.name());
        if(request.email() != null) found.setEmail(request.email());
        if(request.password() != null) found.setPassword(request.password());
        if(request.age() != null) found.setAge(request.age());
        if(request.gender() != null) found.setGender(request.gender());
        Customer updated = repository.save(found);
        return toResponse(updated);
    }

    @Override
    public void delete(UUID id) {
        if(!repository.existsById(id)) throw new CustomerNotFoundException(id);
        repository.deleteById(id);
    }
}
