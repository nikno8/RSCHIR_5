package com.mirea.rschirpractice5.service;

import com.mirea.rschirpractice5.entity.Telephone;
import com.mirea.rschirpractice5.repository.TelephoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TelephoneService {
    private final TelephoneRepository telephoneRepository;

    public List<Telephone> getAllTelephones() {
        return telephoneRepository.findAll();
    }

    public Optional<Telephone> findTelephoneById(Long id) {
        return telephoneRepository.findById(id);
    }

    public Telephone createTelephone(Telephone telephone) {
        return telephoneRepository.save(telephone);
    }

    public void deleteTelephoneById(Long id) {
        telephoneRepository.deleteById(id);
    }
}
