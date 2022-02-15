package ru.linedecor.shop.service.product.brand;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.linedecor.shop.domain.dto.ProductBrandView;
import ru.linedecor.shop.domain.product.ProductBrand;
import ru.linedecor.shop.dto.request.product.brand.BrandDto;
import ru.linedecor.shop.exception.product.brand.ProductBrandAlreadyExistsException;
import ru.linedecor.shop.exception.product.brand.ProductBrandNotFoundException;
import ru.linedecor.shop.repository.product.brand.BrandRepository;
import ru.linedecor.shop.validation.product.brand.Validator;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Transactional
@Service
public class BrandServiceJpa implements BrandService{

    private final BrandRepository brandRepository;
    private final Validator brandValidator;

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

    @Transactional(readOnly = true)
    @Override
    public ProductBrand getBrandById(int id) {
        return findBrandByIdOrElseThrowException(id);
    }

    @Override
    public void createNewBrand(BrandDto brandDto) {
        brandValidator.validate(brandDto);
        checkBrandIsUniqueOrElseThrowBrandAlreadyExistsException(brandDto);
        ProductBrand newBrand = new ProductBrand(brandDto);
        brandRepository.save(newBrand);
    }


    @Override
    public void updateBrand(BrandDto updated) {
        Objects.requireNonNull(updated.getId(), "For update id must not be null!");
        brandValidator.validate(updated);
        ProductBrand fromDB = findBrandByIdOrElseThrowException(updated.getId());
        BeanUtils.copyProperties(updated, fromDB);
    }

    private void checkBrandIsUniqueOrElseThrowBrandAlreadyExistsException(BrandDto newBrand) {
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
