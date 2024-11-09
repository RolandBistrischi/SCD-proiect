package package_tracking.pkg;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import package_tracking.Courier.Courier;

import java.util.List;

@Service
public class PackageService {
    @Autowired
    private PackageRepository packageRepository;

    public Package create(Package myPackage) {
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
}
