package com.saad.spring.fawry.service.impl;

import com.saad.spring.fawry.model.Product;
import com.saad.spring.fawry.repository.ProductRepository;
import com.saad.spring.fawry.service.api.ProductService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product model) {
        if (model.getCode() == null) {
            model.setCode(generateCode(5));
        }
        return repository.save(model);
    }

    @Override
    public Product update(Product model) {
        return repository.save(model);
    }

    @Override
    public Product get(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException(String.format("No Product found with this ID %s", id)));
    }

    @Override
    public Product delete(String id) {
        Product product = repository.findById(id).orElseThrow(() -> new RuntimeException(String.format("No Product found with this ID %s", id)));
        repository.delete(product);
        return product;
    }

    @Override
    public Product activate(String id) {
        Product product = repository.findById(id).orElseThrow(() -> new RuntimeException(String.format("No Product found with this ID %s", id)));
        product.setActive(true);
        return repository.save(product);
    }

    @Override
    public Product deactivate(String id) {
        Product product = repository.findById(id).orElseThrow(() -> new RuntimeException(String.format("No Product found with this ID %s", id)));
        product.setActive(false);
        return repository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Product> findAllAsPage(Integer page, Integer size) {
        Pageable pageWithElements = PageRequest.of(page, size);
        return repository.findAll(pageWithElements).getContent();
    }

    @Override
    public List<Product> findAllActive() {
        return repository.findByActive(true);
    }

    @Override
    public List<Product> findAllActiveAsPage(Integer page, Integer size) {
        Pageable pageWithElements = PageRequest.of(page, size);
        return repository.findByActive(true, pageWithElements);
    }

    @Override
    public List<Product> uploadFile(MultipartFile multipartFile) {
        DataFormatter dataFormatter = new DataFormatter();
        try {
            File temp_file = convert(multipartFile);
            Workbook workbook = WorkbookFactory.create(temp_file);

            Sheet sheet = workbook.getSheetAt(0);
            List<String> rows = new ArrayList<>();
            for (Row row : sheet) {
                for (Cell cell : row) {
                    String cellValue = dataFormatter.formatCellValue(cell);
                    rows.add(cellValue);
                }
            }

            return repository.saveAll(createList(rows, sheet.getRow(0).getLastCellNum()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (OutputStream os = new FileOutputStream(file)) {
            os.write(multipartFile.getBytes());
            os.close();
        }
        return file;
    }

    private List<Product> createList(List<String> rows, int noOfColumns) {
        List<Product> products = new ArrayList<>();
        int i = noOfColumns;
        do {
            Product pro = new Product();

            pro.setCode(rows.get(i));
            pro.setNameAr(rows.get(i + 1));
            pro.setNameEn(rows.get(i + 2));
            pro.setPrice(Double.parseDouble(rows.get(i + 3)));
            pro.setCategoryAr(rows.get(i + 4));
            pro.setCategoryEn(rows.get(i + 5));
            pro.setQuantity(Integer.parseInt(rows.get(i + 6)));
            pro.setActive(true);

            products.add(pro);
            i = i + (noOfColumns);
        } while (i < rows.size());
        return products;
    }

    private String generateCode(int digits) {
        Random rand = new Random(); //instance of random class
        String total_characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < digits; i++) {
            int index = rand.nextInt(total_characters.length() - 1);
            randomString.append(total_characters.charAt(index));
        }
        return randomString.toString();
    }
}
