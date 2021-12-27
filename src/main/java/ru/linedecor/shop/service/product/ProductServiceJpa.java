package ru.linedecor.shop.service.product;

import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.linedecor.shop.domain.dto.ProductView;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceJpa implements ProductService{

    @Override
    public Page<ProductView> getProductPage(Pageable pageable) {
        return null;
    }

    @Override
    public Page<ProductView> getProductPageByNameOrSkuLike(String nameLike, Pageable pageable) {
        return null;
    }
}
