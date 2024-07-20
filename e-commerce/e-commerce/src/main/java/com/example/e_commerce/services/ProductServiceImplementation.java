package com.example.e_commerce.services;

import com.example.e_commerce.config.JwtValidator;
import com.example.e_commerce.entity.Category;
import com.example.e_commerce.entity.Product;

import com.example.e_commerce.exceptions.ProductException;
import com.example.e_commerce.reposatory.CategoryRepositary;
import com.example.e_commerce.reposatory.ProductRepository;
import com.example.e_commerce.request.CreateProductRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class ProductServiceImplementation implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImplementation.class);
    private ProductRepository productRepository;
    private CategoryRepositary categoryRepositary;
    private UserService userService;
    public ProductServiceImplementation(ProductRepository productRepository,CategoryRepositary categoryRepositary,UserService userService){

        this.productRepository=productRepository;
        this.userService=userService;
        this.categoryRepositary=categoryRepositary;
    }
    @Override
    public Product createProduct(CreateProductRequest req) {
        logger.debug("Creating product with request: {}", req);
        Category topLevel = categoryRepositary.findByName(req.getTopLevelCategory());
        if (topLevel == null) {
            logger.debug("Top-level category not found, creating new one");
            Category topLevelCategory = new Category();
            topLevelCategory.setName(req.getTopLevelCategory());
            topLevelCategory.setLevel(1);
            topLevel = categoryRepositary.save(topLevelCategory);
        }
        Category secondLevel = categoryRepositary.findByNameAndParent(req.getSecondLavelCategory(), topLevel.getName());
        if (secondLevel == null) {
            logger.debug("Second-level category not found, creating new one");
            Category secondLavelCategory = new Category();
            secondLavelCategory.setName(req.getSecondLavelCategory());
            secondLavelCategory.setParentCategory(topLevel);
            secondLavelCategory.setLevel(2);
            secondLevel = categoryRepositary.save(secondLavelCategory);
        }
        Category thirdLevel = categoryRepositary.findByNameAndParent(req.getThirdLavelCategory(), secondLevel.getName());
        if (thirdLevel == null) {
            logger.debug("Third-level category not found, creating new one");
            Category thirdLavelCategory = new Category();
            thirdLavelCategory.setName(req.getThirdLavelCategory());
            thirdLavelCategory.setParentCategory(secondLevel);
            thirdLavelCategory.setLevel(3);
            thirdLevel = categoryRepositary.save(thirdLavelCategory);
        }

        // Create a new Product object and set its categories
        Product product =new Product();
        product.setTitle(req.getTitle());
        product.setColor(req.getColor());
        product.setDescription(req.getDescription());
        product.setDiscountedPrice(req.getDiscountedPrice());
        product.setDiscountPersent(req.getDiscountPersent());
        product.setImageUrl(req.getImageUrl());
        product.setBrand(req.getBrand());
        product.setPrice(req.getPrice());
        product.setSizes(req.getSize());
        product.setQuantity(req.getQuantity());
        product.setCategory(thirdLevel);
        product.setCreatedAt(LocalDateTime.now());
        // Save the product to the repository

        Product savedProduct=productRepository.save(product);
        logger.debug("Product saved: {}", savedProduct);
        return savedProduct;
    }

    @Override
    public Product updateProduct(Long Id, Product req) throws ProductException {
        Product product = findProductById(Id);
        if(req.getQuantity()!=0){
            product.setQuantity(req.getQuantity());
        }
        return productRepository.save(product);
    }

    @Override
    public String deleteProduct(Long Id) throws ProductException {
        Product product = findProductById(Id);
        product.getSizes().clear();
        productRepository.delete(product);
        return "Product deleted successfult";
    }

    @Override
    public Product findProductById(Long Id) throws ProductException {
        Optional<Product> opt = productRepository.findById(Id);
        if (opt.isPresent()){
            return opt.get();
        }
        throw new ProductException("Product not found"+ Id);

    }

    @Override
    public List<Product> findAllProduct() throws ProductException {
        try {
            List<Product> products = productRepository.findAll();
            logger.debug("Products retrieved: {}");
            return products;
        } catch (Exception e) {
            logger.error("Error retrieving products");
            throw new ProductException("Error retrieving products");
        }
    }

    @Override
    public Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) throws ProductException {
        logger.info("Fetching products for category: {}", category);
        logger.info("Filter parameters - Colors: {}, Sizes: {}, Min Price: {}, Max Price: {}, Min Discount: {}, Sort: {}, Stock: {}, Page Number: {}, Page Size: {}",
                colors, sizes, minPrice, maxPrice, minDiscount, sort, stock, pageNumber, pageSize);


        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Product> products = productRepository.filterProducts(category,minPrice,maxPrice,minDiscount,sort);
        logger.info("Initial filtered product count: {}", products.size());
        if (!colors.isEmpty()){

            products=products.stream().filter(p-> colors.stream().anyMatch(c-> c.equalsIgnoreCase(p.getColor()))).collect(Collectors.toList());
            logger.info("Filtered by colors, product count: {}", products.size());
        }  if (stock!= null){
              if(stock.equals("in-stock")){
                  products=products.stream().filter(p-> p.getQuantity()>0).collect(Collectors.toList());
                  logger.info("Filtered by in-stock, product count: {}", products.size());
              }

         else if (stock.equals("out_of_stock")) {
            products = products.stream().filter(p -> p.getQuantity() < 1).collect(Collectors.toList());

                  logger.info("Filtered by out_of_stock, product count: {}", products.size());
        }}

        int startindex=(int) pageable.getOffset();
        int endindex=Math.min(startindex + pageable.getPageSize(),products.size());
        // Handle edge cases
        if (startindex >= products.size()) {
            startindex = 0;
            endindex = 0;
        }
        List<Product> productContent=products.subList(startindex,endindex);
        logger.info("Final product list size for the page: {}", productContent.size());
        Page<Product> filtredProducts=new PageImpl<>(productContent,pageable,products.size());
        logger.info("Returning page of products with total elements: {}", filtredProducts.getTotalElements());
        return filtredProducts;
    }


   /* public void  deleteById(long id) {

        Product product = getById(id);
        if (product != null) {
            productRepo.deleteById(id);
        }

    }*/

}
