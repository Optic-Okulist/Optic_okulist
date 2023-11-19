package spring.boot.optic.okulist.repository.glasses;

import java.util.Arrays;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import spring.boot.optic.okulist.model.Glasses;
import spring.boot.optic.okulist.repository.params.serch.SpecificationProvider;

@Component
public class ModelSpecificationProvider implements SpecificationProvider<Glasses> {
    public static final String MODEL = "model";

    @Override
    public String getKey() {
        return MODEL;
    }

    public Specification<Glasses> getSpecification(String[] params) {
        return ((root, query, criteriaBuilder) ->
                root.get(MODEL).in(Arrays.stream(params).toArray()));
    }
}
