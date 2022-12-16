package edu.tienda.core.controllers;

import edu.tienda.core.domain.Cliente;
import edu.tienda.core.exceptions.BadRequestException;
import edu.tienda.core.exceptions.ResourceNotFoundException;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteRestController {

    private List<Cliente> clientes = new ArrayList<>(Arrays.asList(
            new Cliente("arm", "1234", "Armstrgom"),
            new Cliente("ald", "1234", "Aldrin"),
            new Cliente("col", "1234", "Collins")
    ));

    @GetMapping
    public ResponseEntity<?> getClientes(){

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> getCliente(@PathVariable String userName){

        if (userName.length() != 3){
            throw new BadRequestException("EL parametro del nombre debe contener 2 carateres");
        }

        for (Cliente cli: clientes){
            if (cli.getUsername().equalsIgnoreCase(userName)){
                return  ResponseEntity.ok(cli);
            }
        }

        throw new ResourceNotFoundException("CLiente no encontrado");
    }

    @PostMapping
    public ResponseEntity<?> altaCliente(@RequestBody Cliente cliente){
        clientes.add(cliente);

        //Obtenemos la url del servicio al nuevo cliente que se creo
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userName}")
                .buildAndExpand(cliente.getUsername())
                .toUri();

        return ResponseEntity.created(location).body(cliente);
    }

    @PutMapping
    public ResponseEntity<?> modificacionCliente(@RequestBody Cliente cliente){
        Cliente clienteEcontrado = clientes.stream().
                filter(cli -> cli.getUsername().equalsIgnoreCase(cliente.getUsername())).
                findFirst().orElseThrow();

        clienteEcontrado.setPassword(cliente.getPassword());
        clienteEcontrado.setNombre(cliente.getNombre());
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity deleteCLiente(@PathVariable String userName){
        Cliente clienteEncontrado = clientes.stream().
                filter(cli -> cli.getUsername().equalsIgnoreCase(userName)).
                findFirst().orElseThrow();

        clientes.remove(clienteEncontrado);
        return ResponseEntity.noContent().build();
    }

}
