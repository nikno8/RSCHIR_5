package com.mirea.rschirpractice5.controller;


import com.mirea.rschirpractice5.entity.Telephone;
import com.mirea.rschirpractice5.service.TelephoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/telephone")
@RequiredArgsConstructor
public class TelephoneController {
    private final TelephoneService telephoneService;
    @GetMapping
    public ResponseEntity<List<Telephone>> getAllTelephones() {
        List<Telephone> telephones = telephoneService.getAllTelephones();
        return new ResponseEntity<>(telephones, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Telephone> getTelephoneById(@PathVariable Long id) {
        Optional<Telephone> telephone = telephoneService.findTelephoneById(id);
        return telephone.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).
                orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    public ResponseEntity<Telephone> createTelephone(@RequestBody Telephone telephone) {
        Telephone createdTelephone = telephoneService.createTelephone(telephone);
        return new ResponseEntity<>(createdTelephone, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Telephone> updateTelephone(@PathVariable Long id, @RequestBody Telephone updatedTelephone) {
        Optional<Telephone> existingTelephone = telephoneService.findTelephoneById(id);
        if (existingTelephone.isPresent()) {
            Telephone telephone = existingTelephone.get();
            telephone.setName(updatedTelephone.getName());
            telephone.setSellerId(updatedTelephone.getSellerId());
            telephone.setProductType(updatedTelephone.getProductType());
            telephone.setPrice(updatedTelephone.getPrice());
            telephone.setBatteryCapacity(updatedTelephone.getBatteryCapacity());
            telephone.setManufacturer(updatedTelephone.getManufacturer());
            Telephone updated = telephoneService.createTelephone(telephone);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        Optional<Telephone> book = telephoneService.findTelephoneById(id);
        if (book.isPresent()) {
            telephoneService.deleteTelephoneById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
