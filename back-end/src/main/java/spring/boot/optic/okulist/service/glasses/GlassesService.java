package spring.boot.optic.okulist.service.glasses;

import java.util.List;
import org.springframework.data.domain.Pageable;
import spring.boot.optic.okulist.dto.glasses.GlassesRequestDto;
import spring.boot.optic.okulist.dto.glasses.GlassesResponseDto;
import spring.boot.optic.okulist.dto.glasses.GlassesSearchParameter;

public interface GlassesService {
    List<GlassesResponseDto> findAll(Pageable pageable);

    GlassesResponseDto getById(Long id);

    GlassesResponseDto save(GlassesRequestDto glassesRequestDto);

    void deleteById(Long id);

    GlassesResponseDto update(Long id, GlassesRequestDto glassesRequestDto);

    List<GlassesResponseDto> search(GlassesSearchParameter searchParameters);

    List<GlassesResponseDto> findSimilar(GlassesSearchParameter glassesRequestDto);
}
