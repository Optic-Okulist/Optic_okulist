package spring.boot.optic.okulist.repository.glasses;

import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import spring.boot.optic.okulist.model.Glasses;
import spring.boot.optic.okulist.repository.params.serch.SpecificationProvider;

@Component
public class GlassesColorSpecificationProvider implements SpecificationProvider<Glasses> {
    public static final String COLOR = "color";

    @Override
    public String getKey() {
        return COLOR;
    }

    public Specification<Glasses> getSpecification(String[] params) {
        return ((root, query, criteriaBuilder) ->
                root.get(COLOR).in(Arrays.stream(params).toArray()));
    }
}
