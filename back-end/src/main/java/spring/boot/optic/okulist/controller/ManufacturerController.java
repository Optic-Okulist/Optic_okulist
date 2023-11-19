package spring.boot.optic.okulist.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.boot.optic.okulist.dto.contactlenses.manufacturer.ManufacturerRequestDto;
import spring.boot.optic.okulist.dto.contactlenses.manufacturer.ManufacturerResponseDto;
import spring.boot.optic.okulist.service.contactlenses.manufacturer.ManufacturerService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {
    private final ManufacturerService manufacturerService;

    /*
    цей контролер нам потрібен для того щоб створювати виробника.І потім використовувати його при
    створенні нового продукту.

    Продукт можна буде створювати за вробником або за конфігурацією

    LOGIC:
    створити виробника і кожному виробнику сетити потрібні параметри.
    Потім ці параметри які є у виробника використовувувати для
    створення лінз.

    клас Contact Lenses повинен бути головним та давати
    можливість взаємодіяти з класом manufacturer
    and lensConfigModel для того щоб була можливість
    створювати нові товари та використовувати присети
    Це потрібно в тому випадку якщо Лінзи будуть мати однакові параметри
    і щоб не перестворювати потрібні варіанти можна використати пресет(заготовку/шаблон)


     */

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ManufacturerResponseDto createManufacturer(
            @RequestBody @Valid ManufacturerRequestDto manufacturerRequestDto) {
        return manufacturerService.createManufacturer(manufacturerRequestDto);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<ManufacturerResponseDto> getAllManufacturers() {
        return manufacturerService.getAllManufacturers();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ManufacturerResponseDto getManufacturerByName(@PathVariable String name) {
        return manufacturerService.getManufacturerByName(name);
    }
}
