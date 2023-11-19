package spring.boot.optic.okulist.repository.params.serch;

import org.springframework.data.jpa.domain.Specification;
import spring.boot.optic.okulist.dto.glasses.GlassesSearchParameter;

public interface GlassesSpecificationBuilder<T> {
    Specification<T> build(GlassesSearchParameter searchParameters);
}
