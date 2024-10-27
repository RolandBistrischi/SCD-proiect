package package_tracking.pkg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import package_tracking.Courier.Courier;
import package_tracking.Courier.CourierService;


import java.util.List;

@RestController
public class PackageController {
    @Autowired
    private PackageService packageService;
    @Autowired
    private CourierService courierService;

    @PostMapping("/package")
    public Package create(@RequestBody Package myPackage) {
        return packageService.create(myPackage);
    }

    @GetMapping("/package")
    public List<Package> FindAllPackages() {
        return packageService.FindAllPackages();
    }

    @PutMapping("/package")
    public Package updatePackageStatus(@RequestBody UpdatePackageRequest updatePackageRequest) {
        try {
            return packageService.updatePackageStatus(updatePackageRequest);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return null;
    }

    @DeleteMapping("/package")
    public void deletePackage(Package pkg) {
        packageService.delete(pkg);
    }


   /* @PostMapping("/courier")
    public Courier createCourier(@RequestBody Courier courier) {
        return courierService.create(courier);
    }*/
}
