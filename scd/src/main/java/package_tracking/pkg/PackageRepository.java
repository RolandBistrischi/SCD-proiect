package package_tracking.pkg;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import package_tracking.Courier.Courier;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageRepository extends JpaRepository<Package, Integer> {

    List<Package> findByCourier(Courier courier);

    @Query(value = """
            SELECT p FROM Package p WHERE p.status = :status""",
            nativeQuery = true)
    List<Package> findAllByStatus(@Param("status") Status status);

    @Query(value = """
            SELECT * FROM Package p WHERE p.courier_id IS NULL""",
            nativeQuery = true)
    List<Package> findUnassignedPackages();

}
