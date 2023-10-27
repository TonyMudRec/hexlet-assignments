package exercise.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.TargetType;
import org.springframework.beans.factory.annotation.Autowired;

import exercise.model.BaseEntity;
import jakarta.persistence.EntityManager;

// BEGIN
@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING
)
public abstract class ReferenceMapper {

    @Autowired
    private EntityManager em;

    public <T extends BaseEntity> T toEntity(Long id, @TargetType Class<T> entryClass) {
        return id != null ? em.find(entryClass, id) : null;
    }
}
// END
