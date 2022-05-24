package edu.itstep.taxi.repository;

import edu.itstep.taxi.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {

    @Override
    Car save(Car car);

    Car findCarById(long id);

    @Override
    List<Car> findAll();

    @Override
    Page<Car> findAll(Pageable pageable);

    Page<Car> findCarsByYear(short year, Pageable pageable);

    Page<Car> findCarsByEquipment(String equipment, Pageable pageable);

    @Override
    void deleteById(Long id);

}
