package guru.springframework.sfpetclinic.services;

import guru.springframework.sfpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);
}
