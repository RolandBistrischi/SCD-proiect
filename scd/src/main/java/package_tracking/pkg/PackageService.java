package package_tracking.pkg;

import jakarta.persistence.EntityNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import package_tracking.Courier.Courier;
import package_tracking.Courier.CourierRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PackageService {
    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private CourierRepository courierRepository;


    public Package create(Package myPackage) {
        if (myPackage.getStatus() == null || myPackage.getStatus().toString().isEmpty())
            myPackage.setStatus(Status.NEW);

        return packageRepository.save(myPackage);
    }

    public List<Package> FindAllPackages() {
        return packageRepository.findAll();
    }

    public Package updatePackageStatus(@NotNull UpdatePackageRequest updatePackageRequest) {

        Package pack = packageRepository.findById(updatePackageRequest.getId())
                .orElseThrow(() -> new RuntimeException("Pachetul nu a fost găsit!"));

        Status updateStatus = updatePackageRequest.getStatus();
        Status packageStatus = pack.getStatus();

        if (packageStatus.equals(Status.NEW) && updateStatus.equals(Status.PENDING))
            pack.setStatus(updateStatus);
        else if (packageStatus.equals(Status.PENDING) && updateStatus.equals(Status.DELIVERED))
            pack.setStatus(updateStatus);

        return packageRepository.save(pack);
    }

    public void delete(Package mypackage) {
        packageRepository.delete(mypackage);
    }

    public List<Package> getPackagesForCourier(Courier courier) {
        return packageRepository.findByCourier(courier);
    }

    public List<Package> getAllDeliveredPackages() {
        return packageRepository.findAllByStatus(Status.DELIVERED);

    }

    public Optional<Package> updateStatusToDelivered(Integer packageId) {
        Optional<Package> packageOptional = packageRepository.findById(packageId);

        if (packageOptional.isPresent()) {
            Package packageToUpdate = packageOptional.get();
            packageToUpdate.setStatus(Status.DELIVERED);
            packageRepository.save(packageToUpdate);
            return Optional.of(packageToUpdate);
        }
        return Optional.empty();
    }

    public Optional<Package> assignPackageToCourier(Integer packageId, Integer courierId) {
        Optional<Package> packageOptional = packageRepository.findById(packageId);
        Optional<Courier> courierOptional = courierRepository.findById(courierId);

        if (packageOptional.isPresent() && courierOptional.isPresent()) {
            Package packageToUpdate = packageOptional.get();
            Courier courier = courierOptional.get();

            packageToUpdate.setCourier(courier);
            packageRepository.save(packageToUpdate);
            return Optional.of(packageToUpdate);
        }
        return Optional.empty();
    }
}
