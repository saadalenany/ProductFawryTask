package com.saad.spring.fawry.service.api;

import com.saad.spring.fawry.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    Product save(Product model);

    Product update(Product model);

    Product get(String id);

    Product delete(String id);

    Product activate(String id);

    Product deactivate(String id);

    List<Product> findAll();

    List<Product> findAllAsPage(Integer page, Integer size);

    List<Product> findAllActive();

    List<Product> findAllActiveAsPage(Integer page, Integer size);

    List<Product> uploadFile(MultipartFile file);
}
