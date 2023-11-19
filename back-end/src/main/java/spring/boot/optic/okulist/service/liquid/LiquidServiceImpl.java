package spring.boot.optic.okulist.service.liquid;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.dto.liquid.LiquidRequestDto;
import spring.boot.optic.okulist.dto.liquid.LiquidResponseDto;
import spring.boot.optic.okulist.dto.liquid.LiquidSearchParameter;
import spring.boot.optic.okulist.exception.EntityNotFoundException;
import spring.boot.optic.okulist.mapper.LiquidMapper;
import spring.boot.optic.okulist.model.Liquid;
import spring.boot.optic.okulist.repository.liquid.LiquidRepository;
import spring.boot.optic.okulist.specification.liquid.builders.LiquidSpecificationBuilder;

@Service
@RequiredArgsConstructor
public class LiquidServiceImpl implements LiquidService {
    private final LiquidRepository liquidRepository;
    private final LiquidMapper liquidMapper;
    private final LiquidSpecificationBuilder specificationBuilder;

    @Override
    public List<LiquidResponseDto> findAll(Pageable pageable) {
        return liquidRepository.findAll()
                .stream()
                .map(liquidMapper::toDto)
                .toList();
    }

    @Override
    public LiquidResponseDto getById(Long id) {
        Liquid liquid = liquidRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't found Liquid with ID: " + id)
        );
        return liquidMapper.toDto(liquid);
    }

    @Override
    public LiquidResponseDto save(LiquidRequestDto liquidRequestDto) {
        Liquid liquid = liquidMapper.toModel(liquidRequestDto);
        return liquidMapper.toDto(liquidRepository.save(liquid));
    }

    @Override
    public void deleteById(Long id) {
        Liquid liquid = liquidRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't found liquid with ID :" + id)
        );
        liquid.setDeleted(true);
        liquidRepository.save(liquid);
    }

    @Override
    public List<LiquidResponseDto> searchLiquidByParameters(
            LiquidSearchParameter searchParameters) {
        Specification<Liquid> glassSpecification = specificationBuilder
                .build(searchParameters);
        return liquidRepository.findAll(glassSpecification)
                .stream()
                .map(liquidMapper::toDto)
                .toList();
    }

    @Override
    public List<LiquidResponseDto> findSimilar(LiquidSearchParameter liquidRequestDto) {
        Liquid referenceLiquid = liquidMapper.toModelSearchParam(liquidRequestDto);

        List<Liquid> similarLiquids = liquidRepository
                .findByVolumeNotAndPriceNotAndNameAndIdentifierAndDescription(
                referenceLiquid.getVolume(),
                referenceLiquid.getPrice(),
                referenceLiquid.getName(),
                referenceLiquid.getIdentifier(),
                referenceLiquid.getDescription()
        );
        return similarLiquids.stream()
                .map(liquidMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public LiquidResponseDto update(Long id, LiquidRequestDto liquidRequestDto) {
        Liquid existingGlasses = liquidRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Can't find Glasses with ID: " + id));
        BeanUtils.copyProperties(liquidRequestDto, existingGlasses,
                getNullPropertyNames(liquidRequestDto));
        Liquid updatedGlasses = liquidRepository.save(existingGlasses);
        return liquidMapper.toDto(updatedGlasses);
    }

    private String[] getNullPropertyNames(Object source) {
        return getStrings(source);
    }

    public static String[] getStrings(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
