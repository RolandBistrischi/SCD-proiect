package package_tracking.Courier;

import package_tracking.pkg.Package;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import package_tracking.pkg.Status;

import java.util.List;


public interface CourierRepository extends JpaRepository<Courier, Integer> {

    //@Query("SELECT c FROM Courier c WHERE c NOT IN (SELECT p.courier FROM Package p WHERE p.status = :status)")
    @Query("SELECT c FROM Courier c LEFT JOIN Package p ON p.courier.id = c.id WHERE p.id IS NULL AND p.status = :status")
    List<Courier> getAllCouriersWithoutPendingPackages(@Param("status") Status status);

    //@Query("SELECT c.manager.id, COUNT(p) FROM Courier c JOIN c.packages p WHERE p.status = :status GROUP BY c.manager.id")
//    @Query("SELECT c.manager.id, COUNT(p) FROM Courier c JOIN c.packages p WHERE p.status = :status GROUP BY c.manager.id")

    @Query("SELECT c.manager.id, COUNT(p) FROM Courier c JOIN c.packages p WHERE p.status = :status GROUP BY c.manager.id")
    //@Query("SELECT c.manager.id, COUNT(p)")
    List<Object[]> getAllManagersAndDeliveredNumber(@Param("status") Status status);

}