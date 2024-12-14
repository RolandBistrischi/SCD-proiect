package package_tracking.pkg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import package_tracking.Courier.Courier;
import package_tracking.Courier.CourierService;


import java.util.List;
import java.util.Optional;

@RequestMapping("/package")
@RestController
@CrossOrigin
public class PackageController {
    @Autowired
    private PackageService packageService;
    @Autowired
    private CourierService courierService;

    @PostMapping
    public Package create(@RequestBody Package myPackage) {
        return packageService.create(myPackage);
    }

    @GetMapping
    public List<Package> FindAllPackages() {
        return packageService.FindAllPackages();
    }

    @PutMapping
    public Package updatePackageStatus(@RequestBody UpdatePackageRequest updatePackageRequest) {
        try {
            return packageService.updatePackageStatus(updatePackageRequest);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    //sa fac sa se poata da update la tot Package.

    @DeleteMapping
    public void deletePackage(Package pkg) {
        packageService.delete(pkg);
    }

    @GetMapping("/{courier_id}")
    public List<Package> getPackagesForCourier(@PathVariable Integer courier_id) {
        Courier courier = new Courier();
        courier.setId(courier_id);
        try {
            return packageService.getPackagesForCourier(courier);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    @GetMapping("/delivered")
    public List<Package> getAllDeliveredPackages() {
        return packageService.getAllDeliveredPackages();
    }

    @PutMapping("/deliverPackage/{packageId}")
    public ResponseEntity<Package> deliverPackage(@PathVariable Integer packageId) {
        try {
            Optional<Package> deliveredPackage = packageService.updateStatusToDelivered(packageId);
            return deliveredPackage.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/assignPackage/{packageId}/{courierId}")
    public ResponseEntity<Package> assignPackageToCourier(@PathVariable Integer packageId, @PathVariable Integer courierId) {
        try {
            Optional<Package> assignedPackage = packageService.assignPackageToCourier(packageId, courierId);
            return assignedPackage.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
