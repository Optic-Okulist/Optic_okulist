package spring.boot.optic.okulist.service.contactlenses.manufacturer;

import java.util.List;
import spring.boot.optic.okulist.dto.contactlenses.manufacturer.ManufacturerRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.manufacturer.ManufacturerResponseDto;

public interface ManufacturerService {
    ManufacturerResponseDto createManufacturer(ManufacturerRequestDto manufacturerRequestDto);

    ManufacturerResponseDto getManufacturerById(Long manufacturerId);

    List<ManufacturerResponseDto> getAllManufacturers();

    void deleteManufacturer(Long manufacturerId);

    ManufacturerResponseDto getManufacturerByName(String name);
}
