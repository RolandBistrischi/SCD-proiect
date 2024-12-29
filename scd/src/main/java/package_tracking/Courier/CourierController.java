package package_tracking.Courier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import package_tracking.Courier.EmailFolder.EmailRequest;


import java.util.List;
import java.util.logging.Logger;

@RestController
@CrossOrigin
@RequestMapping("/courier")
public class CourierController {
    @Autowired
    private CourierService courierService;
    private static final Logger logger = Logger.getLogger(CourierController.class.getName());

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
    public ResponseEntity<Void> deleteCourier(Courier courier) {
        courierService.delete(courier);
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourierbyId(@PathVariable Integer id) {
        try {
            courierService.deletebyId(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/withoutPendingPackages")
    public List<Courier> getAllCouriersWithoutPendingPackages() {
        return courierService.getAllCouriersWithoutPendingPackages();
    }

    @GetMapping("/managersAndDeliveredNumber")
    public List<ManagerWithDeliveredCount> getAllManagersAndDeliveredNumber() {
        return courierService.getAllManagersAndDeliveredNumber();
    }

    @GetMapping("/managers")
    public ResponseEntity<List<Courier>> getAllManagers() {
        try {
            List<Courier> managers = courierService.getAllManagers();
            return ResponseEntity.ok(managers);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmailToSelected(@RequestBody EmailRequest emailRequest) {
        try {
            System.out.println("inainte de rrimitere");
            courierService.sendEmails(emailRequest);
            System.out.println("skjdfhgbvvvvvv");
            return ResponseEntity.ok("Emails sent successfully");
        } catch (Exception e) {
            String errorMessage = "Error occurred while sending emails: " + e.getMessage();
            logger.severe(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

}
