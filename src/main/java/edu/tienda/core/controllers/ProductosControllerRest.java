package edu.tienda.core.controllers;

import edu.tienda.core.domain.Producto;
import edu.tienda.core.services.ProductService;
import edu.tienda.core.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductosControllerRest {

    //Se instancia la clase de sergicio al estilo "Java Puro"
    //private ProductService productService = new ProductServiceImpl();
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getProductos(){
        //se recuperan todos los productos del servicio
        List<Producto> productos = productService.getProductos();
        return ResponseEntity.ok(productos);
    }
}
