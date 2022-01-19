package ru.linedecor.shop.service.product.brand;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.linedecor.shop.domain.dto.ProductBrandView;
import ru.linedecor.shop.domain.product.ProductBrand;
import ru.linedecor.shop.exception.product.brand.ProductBrandAlreadyExistsException;
import ru.linedecor.shop.exception.product.brand.ProductBrandNotFoundException;
import ru.linedecor.shop.repository.product.brand.BrandRepository;
import ru.linedecor.shop.validation.product.brand.EntityValidator;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Transactional
@Service
public class BrandServiceJpa implements BrandService{

    private final BrandRepository brandRepository;
    private final EntityValidator<ProductBrand> entityValidator;

    @Transactional(readOnly = true)
    @Override
    public List<ProductBrandView> getAllBrandViewsSortByName() {
        return brandRepository.getAllViews(Sort.by("name"));
    }

    @Transactional(readOnly = true)
    @Override
    public ProductBrandView getViewById(int id) {
        return brandRepository.getViewById(id)
                .orElseThrow(() -> new ProductBrandNotFoundException(id));
    }

    @Override
    public ProductBrandView getViewByName(String name) {
        return brandRepository.getViewByName(name)
                .orElseThrow(() -> new ProductBrandNotFoundException(name));
    }

    @Override
    public void createNewBrand(ProductBrand newBrand) {
        entityValidator.validate(newBrand);
        checkBrandIsUniqueOrElseThrowBrandAlreadyExistsException(newBrand);
        brandRepository.save(newBrand);
    }


    @Override
    public void updateBrand(ProductBrand updated) {
        Objects.requireNonNull(updated.getId(), "For update id must not be null!");
        checkBrandIsUniqueOrElseThrowBrandAlreadyExistsException(updated);
        ProductBrand fromDB = findBrandByIdOrElseThrowException(updated.getId());
        BeanUtils.copyProperties(updated, fromDB);
    }

    private void checkBrandIsUniqueOrElseThrowBrandAlreadyExistsException(ProductBrand newBrand) {
        val brandName = newBrand.getName();
        var isExists = brandRepository.existsByName(brandName);
        if (isExists) {
            throw new ProductBrandAlreadyExistsException(brandName);
        }
    }

    @Override
    public void deleteBrandById(int id) {
        ProductBrand fromDB = findBrandByIdOrElseThrowException(id);
        brandRepository.delete(fromDB);
    }

    @Override
    public void deleteBrandByName(String name) {
        ProductBrand fromDB = findBrandByNameOrElseThrowException(name);
        brandRepository.delete(fromDB);
    }

    private ProductBrand findBrandByNameOrElseThrowException(String name) {
        return brandRepository.findByName(name)
                .orElseThrow(() -> new ProductBrandNotFoundException(name));
    }

    private ProductBrand findBrandByIdOrElseThrowException(Integer id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new ProductBrandNotFoundException(id));
    }
}
