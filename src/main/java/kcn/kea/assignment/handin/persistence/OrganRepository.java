package kcn.kea.assignment.handin.persistence;

import kcn.kea.assignment.handin.model.Organ;
import org.springframework.data.repository.CrudRepository;

public interface OrganRepository extends CrudRepository<Organ,Integer>
{
}
