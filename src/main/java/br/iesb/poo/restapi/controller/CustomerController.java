package br.iesb.poo.restapi.controller;

import br.iesb.poo.restapi.dto.CustomerRequest;
import br.iesb.poo.restapi.dto.CustomerResponse;
import br.iesb.poo.restapi.dto.UpdateCustomerRequest;
import br.iesb.poo.restapi.service.ICustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final ICustomerService service;

    public CustomerController(ICustomerService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable UUID id){
        return ResponseEntity.ok(service.findByID(id));
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@RequestBody @Validated CustomerRequest request){
        CustomerResponse res = service.create(request);
        URI location = URI.create("/customers/"+res.id());
        return ResponseEntity.created(location).body(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(@PathVariable UUID id, @RequestBody @Validated UpdateCustomerRequest request){
        CustomerResponse updated = service.update(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
