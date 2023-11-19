package spring.boot.optic.okulist.repository.glasses;

import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.boot.optic.okulist.model.Glasses;

@Repository
public interface GlassesRepository extends JpaRepository<Glasses, Long> {
    //    JpaSpecificationExecutor<Glasses>
    List<Glasses> findAll(Specification<Glasses> specification);

    List<Glasses> findByColorIgnoreCaseAndNameAndPriceAndIdentifierAndDescription(
            String color, String name, double price, String identifier, String description
    );
}
