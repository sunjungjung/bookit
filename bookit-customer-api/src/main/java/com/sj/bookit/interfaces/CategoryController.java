package com.sj.bookit.interfaces;

import com.sj.bookit.application.CategoryService;
import com.sj.bookit.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> list() {
        List<Category> regions = categoryService.getCategories();

        return regions;
    }

}
