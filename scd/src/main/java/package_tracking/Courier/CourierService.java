package package_tracking.Courier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import package_tracking.Courier.EmailFolder.EmailRequest;
import package_tracking.pkg.Status;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import java.util.stream.Collectors;


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

    public Optional<Courier> getCourier(Integer id) {
        return courierRepository.findById(id);
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


    public List<ManagerWithDeliveredCount> getAllManagersAndDeliveredNumber() {
        List<Object[]> results = courierRepository.getAllManagersAndDeliveredNumber();

        Map<Integer, Integer> resultat = results.stream()
                .collect(Collectors.toMap(
                        result -> (Integer) result[0], // courier_id
                        result -> ((Number) result[1]).intValue() // deliveredCount
                ));

        List<ManagerWithDeliveredCount> managerWithDeliveredCountList = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : resultat.entrySet()) {
            Integer courierId = entry.getKey();
            Integer deliveredCount = entry.getValue();

            ManagerWithDeliveredCount man = new ManagerWithDeliveredCount();
            man.setDeliveredCount(deliveredCount);

            Optional<Courier> courier = getCourier(courierId);
            courier.ifPresent(cur -> {
                man.setCourier(cur);
                managerWithDeliveredCountList.add(man);
            });
        }

        return managerWithDeliveredCountList;
    }


    public List<Courier> getAllManagers() {
        List<Integer> managerIds = courierRepository.findAllManagers();
        List<Courier> couriers = new ArrayList<>();

        for (Integer managerId : managerIds) {
            courierRepository.findById(managerId).ifPresent(couriers::add);
        }

        return couriers;
    }


    public void sendEmails(List<String> emails, String subject, String body) throws Exception {
        for (String email : emails) {
            //try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
            //    logger.info("Email sent successfully to: " + email);
            /*} catch (Exception e) {
                logger.severe("Failed to send email to: " + email + " due to " + e.getMessage());
                throw new Exception("Failed to send email to: " + email + " due to " + e.getMessage());
            }*/
        }
    }

    public void sendEmails(EmailRequest emailRequest) throws Exception {
        String body = emailRequest.getBody();
        String subject = emailRequest.getSubject();
        var emails = emailRequest.getEmails();

        System.out.println("inainte de for");
        for (String email : emails) {
            try {
                // Creăm mesajul email bazat pe un model
                SimpleMailMessage message = createEmailMessage(email, subject, body);
                mailSender.send(message);
                System.out.println("Email sent successfully to: " + email);
                logger.info("Email sent successfully to: " + email);

            } catch (Exception e) {
                System.out.println("Failed to send email to: " + email + " due to: " + e.getMessage());
                logger.severe("Failed to send email to: " + email + " due to: " + e.getMessage());
                throw new RuntimeException("Failed to send email to: " + email + " due to " + e.getMessage());
            }
        }
    }

    private SimpleMailMessage createEmailMessage(String email, String subject, String body) {
        System.out.println("in create email");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);
        return message;
    }
}
