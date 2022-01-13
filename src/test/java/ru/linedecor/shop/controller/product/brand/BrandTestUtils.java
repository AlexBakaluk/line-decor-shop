package ru.linedecor.shop.controller.product.brand;

import ru.linedecor.shop.domain.dto.ProductBrandView;

import java.util.List;

public class BrandTestUtils {

    protected static void fillBrandViewsListWithTestData(List<ProductBrandView> brandViews) {
        brandViews.add(new ProductBrandView() {
            @Override
            public Integer getId() {
                return 1;
            }

            @Override
            public String getName() {
                return "Art Line";
            }
        });
        brandViews.add(new ProductBrandView() {
            @Override
            public Integer getId() {
                return 2;
            }

            @Override
            public String getName() {
                return "Decor Line";
            }
        });
        brandViews.add(new ProductBrandView() {
            @Override
            public Integer getId() {
                return 3;
            }

            @Override
            public String getName() {
                return "Home of Decor";
            }
        });
        brandViews.add(new ProductBrandView() {
            @Override
            public Integer getId() {
                return 4;
            }

            @Override
            public String getName() {
                return "Line decore";
            }
        });
        brandViews.add(new ProductBrandView() {
            @Override
            public Integer getId() {
                return 5;
            }

            @Override
            public String getName() {
                return "Line of art";
            }
        });
    }

    protected static void clearBrandViewsList(List<ProductBrandView> brandViews) {
        brandViews.clear();
    }

}
