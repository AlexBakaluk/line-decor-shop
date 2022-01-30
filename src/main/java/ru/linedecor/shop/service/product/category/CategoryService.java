package ru.linedecor.shop.service.product.category;

import ru.linedecor.shop.dto.request.product.category.CategoryRequestDto;
import ru.linedecor.shop.dto.response.product.category.CategoryView;

import java.util.List;

public interface CategoryService {

    CategoryView getCategoryViewById(int id);

    List<CategoryView> getAllCategoryViews();

    List<CategoryView> getAllCategoryViewsWhereNameLike(String nameLike);

    void createNewCategory(CategoryRequestDto newCategory);

    void updateCategory(CategoryRequestDto updated);

    void deleteById(int id);

}
