package package_tracking.Courier;

import package_tracking.pkg.Package;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import package_tracking.pkg.Status;

import java.util.List;


public interface CourierRepository extends JpaRepository<Courier, Integer> {

    @Query(value ="""
                SELECT c.*
                    FROM packagetracking.courier c
                    WHERE c.id NOT IN (SELECT p.courier_id
                        FROM packagetracking.package p
                        WHERE p.status = 1 AND p.courier_id IS NOT NULL)
            """,
            nativeQuery = true
    )
    List<Courier> getAllCouriersWithoutPendingPackages();

   /* @Query(value="""
    SELECT c.manager_id, COUNT(p.id) 
    FROM courier c 
    LEFT JOIN package p ON c.id = p.courier_id 
    WHERE p.status = 2 
    GROUP BY c.manager_id
    HAVING c.manager_id IS NOT NULL
    """, nativeQuery = true)
   // List<ManagerWithDeliveredCount> getAllManagersAndDeliveredNumber();*/

    @Query(value="""
    SELECT
    m.manager_id AS courierId,
    COUNT(p.id) AS deliveredCount
    FROM
            (
                    SELECT
                            c.manager_id
                            FROM
                            courier c
                            LEFT JOIN
                            package p ON c.id = p.courier_id AND p.status = 2
                            WHERE
                            c.manager_id IS NOT NULL
                            GROUP BY
                            c.id, c.manager_id
                            HAVING 
            COUNT(c.manager_id) > 0
            ) AS m
    LEFT JOIN 
   package p ON p.courier_id = m.manager_id AND p.status = 2
    GROUP BY
    m.manager_id;
 """, nativeQuery = true)
    List<Object[]> getAllManagersAndDeliveredNumber();


    @Query(value="SELECT DISTINCT c.manager_id FROM Courier c WHERE c.manager_id IS NOT NULL",nativeQuery = true)
    List<Integer> findAllManagers();
}