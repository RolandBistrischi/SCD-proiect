package package_tracking.Courier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/courier")
public class CourierController {
    @Autowired
    private CourierService courierService;

    @PostMapping
    public ResponseEntity<Courier> create(@RequestBody Courier courier) {
        try {
            Courier createdCourier = courierService.create(courier);
            return ResponseEntity.ok(createdCourier);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping
    public List<Courier> FindAllCourier() {
        return courierService.FindAllCourier();
    }

    @PutMapping
    public Courier updateCourier(@RequestBody Courier updateCourier) {
        try {
            return courierService.updateCourier(updateCourier);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    @DeleteMapping
    public void deleteCourier(Courier courier) {
        courierService.delete(courier);
    }

    @GetMapping("/withoutPendingPackages")
    public List<Courier> getAllCouriersWithoutPendingPackages() {
        return courierService.getAllCouriersWithoutPendingPackages();
    }

    @GetMapping("/managersAndDeliveredNumber")
    public List<Integer> getAllManagersAndDeliveredNumber() {
        return courierService.getAllManagersAndDeliveredNumber();
    }

}
