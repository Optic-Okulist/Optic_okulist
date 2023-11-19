package spring.boot.optic.okulist.repository.params.serch;

public interface SpecificationProviderManager<T> {
    SpecificationProvider<T> getSpecificationProvider(String key);
}
