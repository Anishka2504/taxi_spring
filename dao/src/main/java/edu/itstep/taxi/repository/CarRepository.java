package edu.itstep.taxi.repository;

import edu.itstep.taxi.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {

    @EntityGraph(value = "car")
    @Override
    Car save(Car car);

    @EntityGraph(value = "car")
    Car findCarById(long id);

    @EntityGraph(value = "car")
    @Override
    List<Car> findAll();

    @EntityGraph(value = "car")
    @Override
    Page<Car> findAll(Pageable pageable);

    @EntityGraph(value = "car")
    Page<Car> findCarsByYear(short year, Pageable pageable);

    @EntityGraph(value = "car")
    Page<Car> findCarsByEquipment(String equipment, Pageable pageable);

    @Override
    void deleteById(Long id);

}
