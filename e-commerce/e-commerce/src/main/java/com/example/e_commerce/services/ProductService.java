package com.example.e_commerce.services;

import com.example.e_commerce.entity.Product;
import com.example.e_commerce.exceptions.ProductException;
import com.example.e_commerce.request.CreateProductRequest;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    public Product createProduct(CreateProductRequest req);

    public Product updateProduct(Long Id, Product req)throws ProductException;

    public String deleteProduct(Long Id) throws ProductException;

    public Product findProductById(Long Id) throws ProductException;
    public List<Product> findAllProduct() throws ProductException;

    public Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes,
                                        Integer minPrice, Integer maxPrice, Integer minDiscount, String sort,
                                        String stock, Integer pageNumber, Integer pageSize)throws ProductException;

}
