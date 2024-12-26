package package_tracking.Courier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import package_tracking.pkg.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Service
public class CourierService {
    @Autowired
    private CourierRepository courierRepository;
    @Autowired
    private JavaMailSender mailSender;

    private static final Logger logger = Logger.getLogger(CourierService.class.getName());
    //private static final Logger logger = Logger.getLogger(CourierService.class.getName());

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

    public void deletebyId(Integer courier_id) {
        courierRepository.deleteById(courier_id);
    }

    public List<Courier> getAllCouriersWithoutPendingPackages() {
        return courierRepository.getAllCouriersWithoutPendingPackages();
    }

    /*public List<Integer> getAllManagersAndDeliveredNumber() {
        return courierRepository.getAllManagersAndDeliveredNumber();
    }*/
    public List<ManagerWithDeliveredCount> getAllManagersAndDeliveredNumber() {
        List<Object[]> results = courierRepository.getAllManagersAndDeliveredNumber();
        return results.stream()
                .map(result -> new ManagerWithDeliveredCount((Integer) result[0], ((Number) result[1]).intValue()))
                .toList();
    }


    public List<Courier> getAllManagers() {
        List<Integer> managerIds = courierRepository.findAllManagers();
        List<Courier> couriers = new ArrayList<>();

        for (Integer managerId : managerIds) {
            courierRepository.findById(managerId).ifPresent(couriers::add);
        }

        return couriers;
    }


    public void sendEmails(List<String> emails, String subject, String body) {
        for (String email : emails) {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(email);
                message.setSubject(subject);
                message.setText(body);
                mailSender.send(message);
                logger.info("Email sent successfully to: " + email);
            } catch (Exception e) {
                logger.severe("Failed to send email to: " + email + " due to " + e.getMessage());
            }
        }
    }
}
