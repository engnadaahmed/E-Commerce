package com.example.e_commerce.controler;

import com.example.e_commerce.entity.Category;
import com.example.e_commerce.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private  final CategoryService categoryService;
    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category){
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.addCategory(category)) ;

    }
    @GetMapping
    public ResponseEntity<List<Category>> allCategory(){
        return ResponseEntity.ok(categoryService.allCategory()) ;

    }




}
