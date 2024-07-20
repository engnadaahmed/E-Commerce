package com.example.e_commerce.controler;

import com.example.e_commerce.entity.Product;
import com.example.e_commerce.exceptions.ProductException;
import com.example.e_commerce.services.ProductService;
import com.example.e_commerce.services.ProductServiceImplementation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;
    @GetMapping("/products")
    public ResponseEntity<Page<Product>> findProductByCategoryHandler(@RequestParam String category,
                                                                      @RequestParam List<String> color,
                                                                      @RequestParam List<String> size,
                                                                      @RequestParam Integer minPrice,
                                                                      @RequestParam Integer maxPrice,
                                                                      @RequestParam Integer minDiscount,
                                                                      @RequestParam String sort,
                                                                      @RequestParam String stock,
                                                                      @RequestParam Integer pageNumber,
                                                                      @RequestParam Integer pageSize) throws ProductException {

        logger.info("Received request to find products by category: {}", category);
        logger.info("Filter parameters - Colors: {}, Sizes: {}, Min Price: {}, Max Price: {}, Min Discount: {}, Sort: {}, Stock: {}, Page Number: {}, Page Size: {}",
                color, size, minPrice, maxPrice, minDiscount, sort, stock, pageNumber, pageSize);


        Page<Product> res = productService.getAllProducts(
                category, color, size, minPrice, maxPrice,
                minDiscount, sort, stock, pageNumber, pageSize);
        System.out.println("complete products");
        logger.info("Returning {} products", res.getTotalElements());
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);

    }

    @GetMapping("/products/id/{productId}")
    public ResponseEntity<Product> findProductByldHandler(@PathVariable Long productId) throws ProductException {
        Product product=productService.findProductById(productId);
        return new ResponseEntity<Product>(product,HttpStatus.ACCEPTED);
    }


}
