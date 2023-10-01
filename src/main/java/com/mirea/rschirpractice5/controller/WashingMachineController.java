package com.mirea.rschirpractice5.controller;


import com.mirea.rschirpractice5.entity.WashingMachine;
import com.mirea.rschirpractice5.service.WashingMachineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/washingMachine")
@RequiredArgsConstructor
public class WashingMachineController {
    private final WashingMachineService washingMachineService;
    @GetMapping
    public ResponseEntity<List<WashingMachine>> getAllWashingMachines() {
        List<WashingMachine> washingMachines = washingMachineService.getAllWashingMachines();
        return new ResponseEntity<>(washingMachines, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<WashingMachine> getWashingMachineById(@PathVariable Long id) {
        Optional<WashingMachine> washingMachine = washingMachineService.findWashingMachineById(id);
        return washingMachine.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).
                orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    public ResponseEntity<WashingMachine> createWashingMachine(@RequestBody WashingMachine washingMachine) {
        WashingMachine createdWashingMachine = washingMachineService.createWashingMachine(washingMachine);
        return new ResponseEntity<>(createdWashingMachine, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WashingMachine> updateWashingMachine(@PathVariable Long id, @RequestBody WashingMachine updatedWashingMachine) {
        Optional<WashingMachine> existingWashingMachine = washingMachineService.findWashingMachineById(id);
        if (existingWashingMachine.isPresent()) {
            WashingMachine washingMachine = existingWashingMachine.get();
            washingMachine.setName(updatedWashingMachine.getName());
            washingMachine.setSellerId(updatedWashingMachine.getSellerId());
            washingMachine.setProductType(updatedWashingMachine.getProductType());
            washingMachine.setPrice(updatedWashingMachine.getPrice());
            washingMachine.setManufacturer(updatedWashingMachine.getManufacturer());
            WashingMachine updated = washingMachineService.createWashingMachine(washingMachine);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWashingMachine(@PathVariable Long id) {
        Optional<WashingMachine> washingMachine = washingMachineService.findWashingMachineById(id);
        if (washingMachine.isPresent()) {
            washingMachineService.deleteWashingMachineById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
