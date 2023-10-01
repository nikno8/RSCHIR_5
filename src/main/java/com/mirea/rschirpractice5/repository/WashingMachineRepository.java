package com.mirea.rschirpractice5.repository;

import com.mirea.rschirpractice5.entity.WashingMachine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WashingMachineRepository extends JpaRepository <WashingMachine,Long> {
}
