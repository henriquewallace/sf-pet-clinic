package guru.springframework.sfpetclinic.services.springdatajpa;

import guru.springframework.sfpetclinic.model.Specialty;
import guru.springframework.sfpetclinic.repositories.SpecilityRepository;
import guru.springframework.sfpetclinic.services.SpecialtyService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class SpecialtySDJpaService implements SpecialtyService {

    private final SpecilityRepository specilityRepository;

    public SpecialtySDJpaService(SpecilityRepository specilityRepository) {
        this.specilityRepository = specilityRepository;
    }

    @Override
    public Set<Specialty> findAll() {
        Set<Specialty> specialties = new HashSet<>();
        specilityRepository.findAll().forEach(specialties::add);
        return specialties;
    }

    @Override
    public Specialty findById(Long aLong) {
        return specilityRepository.findById(aLong).orElse(null);
    }

    @Override
    public Specialty save(Specialty object) {
        return specilityRepository.save(object);
    }

    @Override
    public void delete(Specialty object) {
        specilityRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        specilityRepository.deleteById(aLong);
    }
}
