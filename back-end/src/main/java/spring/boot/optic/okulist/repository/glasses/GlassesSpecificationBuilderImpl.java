//package spring.boot.optic.okulist.repository.glasses;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.stereotype.Component;
//import spring.boot.optic.okulist.dto.glasses.GlassesSearchParameter;
//import spring.boot.optic.okulist.model.Glasses;
//import spring.boot.optic.okulist.repository.params.serch.SpecificationProviderManager;
//import spring.boot.optic.okulist.repository.params.serch.GlassesSpecificationBuilder;
//
//@RequiredArgsConstructor
//@Component
//public class GlassesSpecificationBuilderImpl implements GlassesSpecificationBuilder<Glasses> {
//    public static final String MODEL = "model";
//    public static final String MANUFACTURER = "manufacturer";
//    public static final String COLOR = "color";
//    private final SpecificationProviderManager<Glasses> glassesSpecificationProviderManager;
//
//    @Override
//    public Specification<Glasses> build(GlassesSearchParameter searchParameters) {
//        Specification<Glasses> specification = Specification.where(null);
//        if (searchParameters.model() != null && searchParameters.model().length > 0) {
//            specification = specification.and(glassesSpecificationProviderManager
//                    .getSpecificationProvider(MODEL)
//                    .getSpecification(searchParameters.model()));
//        }
//        if (searchParameters.manufacturer() != null
//        && searchParameters.manufacturer().length > 0) {
//            specification = specification.and(glassesSpecificationProviderManager
//                    .getSpecificationProvider(MANUFACTURER)
//                    .getSpecification(searchParameters.manufacturer()));
//        }
//        if (searchParameters.color() != null && searchParameters.color().length > 0) {
//            specification = specification.and(glassesSpecificationProviderManager
//                    .getSpecificationProvider(COLOR)
//                    .getSpecification(searchParameters.color()));
//        }
//        return specification;
//    }
//}
