package com.mirea.rschirpractice5.service;

import com.mirea.rschirpractice5.entity.Telephone;
import com.mirea.rschirpractice5.entity.WashingMachine;
import com.mirea.rschirpractice5.repository.WashingMachineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WashingMachineService {
    private final WashingMachineRepository washingMachineRepository;



    public List<WashingMachine> getAllWashingMachines() {
        return washingMachineRepository.findAll();
    }

    public Optional<WashingMachine> findWashingMachineById(Long id) {
        return washingMachineRepository.findById(id);

    }

    public WashingMachine createWashingMachine(WashingMachine washingMachine) {
        return washingMachineRepository.save(washingMachine);
    }

    public void deleteWashingMachineById(Long id) {
        washingMachineRepository.deleteById(id);
    }
}
