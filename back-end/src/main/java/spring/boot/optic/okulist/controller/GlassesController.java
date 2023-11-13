package spring.boot.optic.okulist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.optic.okulist.dto.glasses.GlassesRequestDto;
import spring.boot.optic.okulist.dto.glasses.GlassesResponseDto;
import spring.boot.optic.okulist.dto.glasses.GlassesSearchParameter;
import spring.boot.optic.okulist.service.glasses.GlassesService;

@Tag(name = "Glasses Controller",
        description = "Endpoints for managing glasses")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/glasses")
public class GlassesController {
    private static final Logger logger = LogManager.getLogger(GlassesController.class);
    private final GlassesService glassesService;

    @Operation(summary = "Create a Glasses",
            description = "Creates a new Glasses in shop list.")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public GlassesResponseDto creatGlasses(@RequestBody
                                              @Valid GlassesRequestDto glassesRequestDto) {
        logger.info("Creating a new glasses.");
        return glassesService.save(glassesRequestDto);
    }

    @Operation(summary = "Get All glasses ")
    @GetMapping
    public List<GlassesResponseDto> getAll(Pageable pageable) {
        return glassesService.findAll(pageable);
    }
    // думаю нужно использовать нейм и оно у нас дублируеться

    @Operation(summary = "Get Glasses by ID")
    @GetMapping("/{id}")
    public GlassesResponseDto getGlassesById(@PathVariable Long id) {
        logger.info("Retrieving category with ID: " + id);
        return glassesService.getById(id);
    }

    @Operation(summary = "Update Glasses by ID")
    @PutMapping("/{id}")
    public GlassesResponseDto updateGlasses(@PathVariable Long id,
                                            @RequestBody GlassesRequestDto glassesRequestDto) {
        logger.info("Updating category with ID: " + id);
        return glassesService.update(id, glassesRequestDto);
    }

    @Operation(summary = "Search for glass",
            description = "Searches for glass in the store based on "
                    + "various search parameters such as color, manufacturer, or model."
    )
    @GetMapping("/search") // http://localhost:8080/api/glasses/search?color=red
    public List<GlassesResponseDto> searchBooks(GlassesSearchParameter searchParameters) {
        return glassesService.searchGlassesByParameters(searchParameters);
    }

    @Operation(summary = "Delete glasses by their ID")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteGlasses(@PathVariable Long id) {
        logger.info("Deleting glasses with ID: " + id);
        glassesService.deleteById(id);
    }
}