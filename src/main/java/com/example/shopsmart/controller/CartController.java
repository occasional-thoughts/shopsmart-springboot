package com.example.shopsmart.controller;

import com.example.shopsmart.entity.Cart;
import com.example.shopsmart.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService service;
    public CartController(CartService service) { this.service = service; }

    @GetMapping
    public List<Cart> list() { return service.getAllCarts(); }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> get(@PathVariable Long id) {
        return service.getCartById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cart add(@RequestBody Cart c) { return service.addCart(c); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteCart(id);
        return ResponseEntity.noContent().build();
    }
}
