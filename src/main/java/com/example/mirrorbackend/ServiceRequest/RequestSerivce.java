package com.example.mirrorbackend.ServiceRequest;

import com.example.mirrorbackend.Customer.Customer;
import com.example.mirrorbackend.Service.SalonService;
import com.example.mirrorbackend.Service.SalonServiceRepository;
import com.example.mirrorbackend.Service.SaloneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class RequestSerivce {
    private final ServiceRequestRepository serviceRequestRepository;
    private final SaloneService saloneService;

    public List<ServiceRequest> findAllServices(){
        return serviceRequestRepository.findAll();
    }
    public ServiceRequest getService(String id){
        return serviceRequestRepository.findById(id).get();
    }
    public void createServiceRequest(ServiceRequest serviceRequest){
        serviceRequestRepository.save(serviceRequest);
    }
    public void deleteService(String id){
        serviceRequestRepository.deleteById(id);
    }
    public void addCustomerToServiceRequest(Customer customer, ServiceRequest request){
        request.setCustomer(customer);
        serviceRequestRepository.save(request);
    }
    public void addServiceToRequest(SalonService salonService, ServiceRequest serviceRequest){
        serviceRequest.getServices().add(salonService);
        serviceRequest.setTotal(serviceRequest.getTotal() + salonService.ChosenPrice);
        saloneService.addRequestToService(serviceRequest, salonService);
        serviceRequestRepository.save(serviceRequest);
    }


}
