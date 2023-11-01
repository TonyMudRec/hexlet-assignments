package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

// BEGIN
@Component
public class ProductSpecification {
    public Specification<Product> build(ProductParamsDTO params) {
        return withCategory(params.getCategoryId())
                .and(withPriceGt(params.getPriceGt()))
                .and(withPriceLt(params.getPriceLt()))
                .and(withRatingGt(params.getRatingGt()))
                .and(withTitleCont(params.getTitleCont()));
    }

    public Specification<Product> withCategory(Long categoryId) {
        return ((root, query, criteriaBuilder) -> categoryId == null ? criteriaBuilder.conjunction()
                : criteriaBuilder.equal(root.get("category").get("id"), categoryId));
    }

    public Specification<Product> withPriceGt(Integer price) {
        return ((root, query, criteriaBuilder) -> price == null ? criteriaBuilder.conjunction()
                : criteriaBuilder.greaterThan(root.get("price"), price));
    }

    public Specification<Product> withPriceLt(Integer price) {
        return ((root, query, criteriaBuilder) -> price == null ? criteriaBuilder.conjunction()
                : criteriaBuilder.lessThan(root.get("price"), price));
    }

    public Specification<Product> withRatingGt(Double rating) {
        return ((root, query, criteriaBuilder) -> rating == null ? criteriaBuilder.conjunction()
                : criteriaBuilder.greaterThan(root.get("rating"), rating));
    }

    public Specification<Product> withTitleCont(String title) {
        return ((root, query, criteriaBuilder) -> title == null ? criteriaBuilder.conjunction()
                : criteriaBuilder.like(root.get("title"), title));
    }
}
// END
