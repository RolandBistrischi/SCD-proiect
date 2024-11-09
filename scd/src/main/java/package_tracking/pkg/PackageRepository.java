package package_tracking.pkg;

import package_tracking.Courier.Courier;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageRepository extends JpaRepository<Package, Integer> {

    List<Package> findByCourier(Courier courier);
}
