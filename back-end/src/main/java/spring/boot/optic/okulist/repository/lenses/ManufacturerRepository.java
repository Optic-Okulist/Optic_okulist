package spring.boot.optic.okulist.repository.lenses;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import spring.boot.optic.okulist.model.lenses.parameters.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    Optional<Object> findByName(String name);
}
