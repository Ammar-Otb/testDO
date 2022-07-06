package com.example.mirrorbackend.ServiceRequest;

import com.example.mirrorbackend.API.API;
import com.example.mirrorbackend.Customer.Customer;
import com.example.mirrorbackend.Customer.CustomerSerivce;
import com.example.mirrorbackend.Service.SalonService;
import com.example.mirrorbackend.Service.SaloneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController @RequestMapping("api/v1/request") @RequiredArgsConstructor
public class RequestController {
    private final RequestSerivce requestSerivce;
    private final SaloneService saloneService;
    private final CustomerSerivce customerSerivce;
    @GetMapping
    public ResponseEntity getAllServices(){
        return ResponseEntity.ok(requestSerivce.findAllServices());
    }
    @GetMapping("{id}")
    public ResponseEntity getService(@PathVariable String id){
        if(requestSerivce.getService(id) == null){
            return ResponseEntity.badRequest().body(new API("Invalid Request ID", 401));
        }
        return ResponseEntity.ok(requestSerivce.getService(id));
    }
    @PostMapping("/create/{customerId}")
    public ResponseEntity createService(@RequestBody @Valid ServiceRequest serviceRequest, @PathVariable String customerId){
        if(requestSerivce == null){return ResponseEntity.badRequest().body(new API("Invalid Data Entered", 401));}

        requestSerivce.addCustomerToServiceRequest(customerSerivce.getCustomer(customerId), serviceRequest);
        requestSerivce.createServiceRequest(serviceRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(new API("Created Service request", 201));
    }

    @PostMapping("/add/service/{salonServiceID}/{requestID}")
    public ResponseEntity addService(@PathVariable String salonServiceID, @PathVariable String requestID){
        if(saloneService.getService(salonServiceID) == null){return ResponseEntity.badRequest().body(new API("Invalid Data Entered", 401));}
        requestSerivce.addServiceToRequest(saloneService.getService(salonServiceID),requestSerivce.getService(requestID) );
        return ResponseEntity.status(HttpStatus.CREATED).body(new API("Added Successfully", 201));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteService(@PathVariable String id){
        if(requestSerivce.getService(id) == null){
            return ResponseEntity.badRequest().body(new API("Invalid Service ID", 401));
        }
        requestSerivce.deleteService(id);
        return ResponseEntity.ok(new API("Removed Service Request Successfully", 201));
    }
}
