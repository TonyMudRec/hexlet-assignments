package exercise.mapper;

// BEGIN

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.model.Product;
import org.mapstruct.*;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class ProductMapper {
    @Mapping(target = "cost", source = "price")
    @Mapping(target = "name", source = "title")
    @Mapping(target = "barcode", source = "vendorCode")
    public abstract Product map(ProductCreateDTO productCreateDTO);
    @Mapping(target = "price", source = "cost")
    @Mapping(target = "title", source = "name")
    @Mapping(target = "vendorCode", source = "barcode")
    public abstract ProductDTO map(Product product);
    @Mapping(target = "name", source = "title", ignore = true)
    @Mapping(target = "cost", source = "price")
    public abstract void update(ProductUpdateDTO productUpdateDTO, @MappingTarget Product product);
}
// END
