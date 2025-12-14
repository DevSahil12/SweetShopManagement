package org.example.sweetshopmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.example.sweetshopmanagement.model.Sweet;
import org.example.sweetshopmanagement.service.SweetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/sweets")
@RequiredArgsConstructor
public class SweetController {
    private final SweetService sweetService;

    @GetMapping
    public List<Sweet> getAll(){
        return sweetService.getAllSweets();
    }
    @PostMapping
    public Sweet create(@RequestBody Sweet sweet){
        return sweetService.addSweet(sweet);
    }
    @PutMapping("/{id}")
    public Sweet update(@PathVariable String id,@RequestBody Sweet sweet){
        return sweetService.updateSweet(id,sweet);
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id){
        sweetService.deleteSweet(id);
        return "Deleted";
    }
    @PostMapping("/{id}/purchase")
    public Sweet purchase(@PathVariable String id){
        return sweetService.purchaseSweet(id);
    }
    @PostMapping("/{id}/restock")
    public Sweet restock(@PathVariable String id, @RequestBody Map<String, Integer> body) {
        int qty = body.get("qty");
        return sweetService.restockSweet(id, qty);
    }

}
