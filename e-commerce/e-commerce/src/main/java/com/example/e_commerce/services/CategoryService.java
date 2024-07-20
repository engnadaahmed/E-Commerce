package com.example.e_commerce.services;

import com.example.e_commerce.entity.Category;

import com.example.e_commerce.reposatory.Repo_Categ;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final Repo_Categ categRepo;

    public List<Category> allCategory(){

        return  categRepo.findAll();
    }
    public Category addCategory(Category category){
        return categRepo.save(category);

    }

}
