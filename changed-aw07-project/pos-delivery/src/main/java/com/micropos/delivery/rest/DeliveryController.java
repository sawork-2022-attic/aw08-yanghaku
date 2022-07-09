package com.micropos.delivery.rest;


import com.micropos.api.DeliveryApi;
import com.micropos.delivery.repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class DeliveryController implements DeliveryApi {
    DeliveryRepository deliveryRepository;

    @Autowired
    public void setDeliveryRepository(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public ResponseEntity<String> deliveryStatusById(Integer orderId) {
        return new ResponseEntity<>(deliveryRepository.findOrderStatusById(orderId), HttpStatus.OK);
    }
}
