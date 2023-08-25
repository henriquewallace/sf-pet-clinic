package guru.springframework.sfpetclinic.repositories;

import guru.springframework.sfpetclinic.model.Specialty;
import org.springframework.data.repository.CrudRepository;

public interface SpecilityRepository extends CrudRepository<Specialty, Long> {
}
