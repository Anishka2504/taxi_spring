package edu.itstep.taxi.repository;

import edu.itstep.taxi.entity.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends PagingAndSortingRepository<Driver, Long> {

    @Override
    Driver save(Driver driver);

    Driver findDriverById(long id);

    @Override
    Page<Driver> findAll(Pageable pageable);

    Page<Driver> findAllByLastNameContains(String lastName, Pageable pageable);

    Driver findByDriverLicence(String licence);

    @Override
    void deleteById(Long id);
}
