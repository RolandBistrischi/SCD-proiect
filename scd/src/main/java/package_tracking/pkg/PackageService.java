package package_tracking.pkg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
