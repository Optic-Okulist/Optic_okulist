package spring.boot.optic.okulist.dto.glasses;

public record GlassesSearchParameter(
        String color,
        String model,
        String manufacturer,
        String identifier
) {
}
