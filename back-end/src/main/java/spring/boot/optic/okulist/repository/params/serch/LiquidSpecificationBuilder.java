package spring.boot.optic.okulist.repository.params.serch;

import org.springframework.data.jpa.domain.Specification;
import spring.boot.optic.okulist.dto.liquid.LiquidSearchParameter;

public interface LiquidSpecificationBuilder<T> {
    Specification<T> build(LiquidSearchParameter searchParameters);
}
