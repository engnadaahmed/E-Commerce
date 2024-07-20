package com.example.e_commerce.controler;

import com.example.e_commerce.entity.Product;
import com.example.e_commerce.exceptions.ProductException;
import com.example.e_commerce.request.CreateProductRequest;
import com.example.e_commerce.response.ApiResponse;
import com.example.e_commerce.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {
    private static final Logger logger = LoggerFactory.getLogger(AdminProductController.class);

    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequest req){
        logger.debug("Received product creation request: {}", req);
        Product product=productService.createProduct(req);
        logger.debug("Created product: {}", product);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }



    @GetMapping("/all")
    public ResponseEntity<List<Product>> findAllProduct() throws ProductException {
        List<Product> products =productService.findAllProduct();
        logger.debug("All product: {}");
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) throws ProductException {

        productService.deleteProduct(productId);
        ApiResponse res = new ApiResponse();
        res.setMessage("product deleted successfully");
        res.setStatus(true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @PutMapping("/{productId}/update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product req,@PathVariable Long productId)throws ProductException{
        Product product=productService.updateProduct(productId,req);
        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }
    @PostMapping("/creates")
    public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductRequest[] req)  {

       for(CreateProductRequest product:req){

           productService.createProduct(product);
       }
        ApiResponse res = new ApiResponse();
        res.setMessage("product created successfully");
        res.setStatus(true);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

}
