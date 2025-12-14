package org.example.sweetshopmanagement.service;
import org.example.sweetshopmanagement.model.Sweet;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public interface SweetService {
    Sweet addSweet(Sweet sweet);
    List<Sweet> getAllSweets();
    List<Sweet> search(String name, String category, Double minPrice, Double maxPrice);
    Sweet updateSweet(String id, Sweet updated);
    void deleteSweet(String id);
    Sweet purchaseSweet(String id);
    Sweet restockSweet(String id, int quantity);
}

