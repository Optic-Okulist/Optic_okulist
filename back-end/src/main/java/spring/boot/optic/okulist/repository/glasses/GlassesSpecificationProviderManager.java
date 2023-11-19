package spring.boot.optic.okulist.repository.glasses;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import spring.boot.optic.okulist.model.Glasses;
import spring.boot.optic.okulist.repository.params.serch.SpecificationProvider;
import spring.boot.optic.okulist.repository.params.serch.SpecificationProviderManager;

@RequiredArgsConstructor
@Component
public class GlassesSpecificationProviderManager implements SpecificationProviderManager<Glasses> {

    private final List<SpecificationProvider<Glasses>> glassesSpecificationProviders;

    @Override
    public SpecificationProvider<Glasses> getSpecificationProvider(String key) {
        return glassesSpecificationProviders.stream()
                .filter(b -> b.getKey().equals(key))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Can't find specification provider for key " + key));
    }
}
