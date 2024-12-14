package package_tracking.Courier;

import package_tracking.pkg.Package;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import package_tracking.pkg.Status;

import java.util.List;


public interface CourierRepository extends JpaRepository<Courier, Integer> {

    @Query(value ="""
            SELECT c.* FROM courier c 
            WHERE c.id NOT IN (SELECT p.courier_id FROM package p WHERE p.status = 'PENDING')
            """,
            nativeQuery = true
    )
    List<Courier> getAllCouriersWithoutPendingPackages();

    @Query(value="""
    SELECT c.manager_id, COUNT(p.id) 
    FROM courier c 
    LEFT JOIN package p ON c.id = p.courier_id 
    WHERE p.status = 'DELIVERED' 
    GROUP BY c.manager_id
    HAVING c.manager_id IS NOT NULL
    """, nativeQuery = true)
    List<Object[]> getAllManagersAndDeliveredNumber();


    @Query(value="SELECT DISTINCT c.manager_id FROM Courier c WHERE c.manager_id IS NOT NULL",nativeQuery = true)
    List<Integer> findAllManagers();
}