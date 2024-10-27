/*package package_tracking.Courier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
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


    /*@PostMapping
    public Courier create(@RequestBody Courier courier) {
        return courierService.create(courier);
    }*/

  /*  @GetMapping
    public List<Courier> FindAllCourier() {
        return courierService.FindAllCourier();
    }

   /* @PutMapping("/courier")
    public Courier updateCourier(@RequestBody UpdatePackageRequest updatePackageRequest) {
        try {
            return courierService.updatePackageStatus(updatePackageRequest);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }*/

   /* @DeleteMapping
    public void deleteCourier(Courier courier) {
        courierService.delete(courier);
    }

}
*/