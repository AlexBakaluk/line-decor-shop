package ru.linedecor.shop.controller.product.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.linedecor.shop.dto.request.product.category.CategoryRequestDto;
import ru.linedecor.shop.dto.response.product.category.CategoryView;
import ru.linedecor.shop.service.product.category.CategoryService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/product/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(path = "/id/{id}")
    public CategoryView getCategoryById(@PathVariable int id) {
        return categoryService.getCategoryViewById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping
    public List<CategoryView> getAllCategories() {
        return categoryService.getAllCategoryViews();
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping(path = "/nameLike/{nameLike}")
    public List<CategoryView> getAllCategoriesWhereNameLike(@PathVariable String nameLike) {
        return categoryService.getAllCategoryViewsWhereNameLike(nameLike);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createNewCategory(@RequestBody CategoryRequestDto newCategory) {
        categoryService.createNewCategory(newCategory);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void updateCategory(@RequestBody CategoryRequestDto updated) {
        categoryService.updateCategory(updated);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/id/{id}")
    public void deleteCategoryById(@PathVariable int id) {
        categoryService.deleteById(id);
    }

    @GetMapping(path = "/productId/{productId}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<CategoryView> getCategoriesByProductId(@PathVariable Long productId) {
        return categoryService.getAllCategoryViewsByProductId(productId);
    }

}
