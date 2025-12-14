package org.example.sweetshopmanagement.service;


import lombok.RequiredArgsConstructor;
import org.example.sweetshopmanagement.model.Sweet;
import org.example.sweetshopmanagement.repo.SweetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SweetServiceImpl implements SweetService {

    private final SweetRepository repo;

    @Override
    public Sweet addSweet(Sweet sweet) {
        return repo.save(sweet);
    }

    @Override
    public List<Sweet> getAllSweets() {
        return repo.findAll();
    }

    @Override
    public List<Sweet> search(String name, String category, Double min, Double max) {
        if (name != null) return repo.findByNameContainingIgnoreCase(name);
        if (category != null) return repo.findByCategoryIgnoreCase(category);
        if (min != null && max != null) return repo.findByPriceBetween(min, max);
        return repo.findAll();
    }

    @Override
    public Sweet updateSweet(String id, Sweet updated) {
        Sweet sweet = repo.findById(id).orElseThrow();
        sweet.setName(updated.getName());
        sweet.setCategory(updated.getCategory());
        sweet.setPrice(updated.getPrice());
        sweet.setQuantity(updated.getQuantity());
        return repo.save(sweet);
    }

    @Override
    public void deleteSweet(String id) {
        repo.deleteById(id);
    }

    @Override
    public Sweet purchaseSweet(String id) {
        Sweet s = repo.findById(id).orElseThrow();
        if (s.getQuantity() <= 0)
            throw new RuntimeException("Out of stock");

        s.setQuantity(s.getQuantity() - 1);
        return repo.save(s);
    }

    @Override
    public Sweet restockSweet(String id, int qty) {
        Sweet s = repo.findById(id).orElseThrow();
        s.setQuantity(s.getQuantity() + qty);
        return repo.save(s);
    }
}


