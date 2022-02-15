package ru.linedecor.shop.service.product.category;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.linedecor.shop.domain.product.Category;
import ru.linedecor.shop.dto.request.product.category.CategoryRequestDto;
import ru.linedecor.shop.dto.response.product.category.CategoryView;
import ru.linedecor.shop.exception.product.category.CategoryAlreadyExistsException;
import ru.linedecor.shop.exception.product.category.CategoryNotFoundException;
import ru.linedecor.shop.repository.product.category.CategoryRepository;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceJpa implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    @Override
    public CategoryView getCategoryViewById(int id) {
        return categoryRepository.getViewById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryView> getAllCategoryViews() {
        return categoryRepository.getAllCategoryViews();
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryView> getAllCategoryViewsWhereNameLike(String nameLike) {
        return categoryRepository.getAllCategoryViewsWhereNameLike(nameLike);
    }

    @Override
    public void createNewCategory(CategoryRequestDto requestCategory) {
        checkIsUniqueCategoryOrElseThrowException(requestCategory);
        Category newCategory = new Category();
        BeanUtils.copyProperties(requestCategory, newCategory);
        categoryRepository.save(newCategory);
    }

    @Override
    public void updateCategory(CategoryRequestDto updated) {
        val id = updated.getId();
        Objects.requireNonNull(id, "Id must not be null for update");
        Category fromDB = findCategoryByIdOrElseThrowException(id);
        BeanUtils.copyProperties(updated, fromDB);
    }


    private void checkIsUniqueCategoryOrElseThrowException(CategoryRequestDto newCategory) {
        val categoryName = newCategory.getName();
        boolean isExists = categoryRepository.existsByNameIgnoreCase(categoryName);
        if (isExists) {
            throw new CategoryAlreadyExistsException(categoryName);
        }
    }

    private Category findCategoryByIdOrElseThrowException(Integer id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public void deleteById(int id) {
        Category fromDB = findCategoryByIdOrElseThrowException(id);
        categoryRepository.delete(fromDB);
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Category> getCategoriesByIds(Set<Integer> categoryIds) {
        return categoryRepository.findAllByIdIn(categoryIds);
    }

    @Override
    public List<CategoryView> getAllCategoryViewsByProductId(Long productId) {
        return null;
    }
}
