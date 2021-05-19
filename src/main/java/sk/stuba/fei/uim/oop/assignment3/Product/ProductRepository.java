package sk.stuba.fei.uim.oop.assignment3.Product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    List<Product> findAll();
    Optional<Product> findById(Long id);
}
