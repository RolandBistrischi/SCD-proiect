package package_tracking.Courier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import package_tracking.pkg.Status;

import java.util.List;


@Service
public class CourierService {
    @Autowired
    private CourierRepository courierRepository;

    public Courier create(Courier courier) {
        return courierRepository.save(courier);
    }

    public List<Courier> FindAllCourier() {
        return courierRepository.findAll();
    }

    public Courier updateCourier(Courier updateCourier) {

        Courier courier = courierRepository.findById(updateCourier.getId())
                .orElseThrow(() -> new RuntimeException("Curierul nu a fost gasit!"));

        //courier=updateCourier;

        return courierRepository.save(updateCourier);


        /*Courier courier = packageRepository.findById(updatePackageRequest.getId())
                .orElseThrow(() -> new RuntimeException("Pachetul nu a fost găsit!"));

        Status updateStatus=updatePackageRequest.getStatus();
        Status packageStatus=pack.getStatus();

        if (packageStatus.equals(Status.NEW) && updateStatus.equals(Status.PENDING))
            pack.setStatus(updateStatus);
        else
        if(packageStatus.equals(Status.PENDING) && updateStatus.equals(Status.DELIVERED))
            pack.setStatus(updateStatus);

        return packageRepository.save(pack);*/
    }

    public void delete(Courier courier) {
        courierRepository.delete(courier);
    }

    public List<Courier> getAllCouriersWithoutPendingPackages() {
        return courierRepository.getAllCouriersWithoutPendingPackages(Status.PENDING);
    }

    public List<Object[]> getAllManagersAndDeliveredNumber() {
        return courierRepository.getAllManagersAndDeliveredNumber(Status.DELIVERED);
    }
}
