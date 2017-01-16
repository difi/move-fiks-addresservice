package repositories

import no.difi.ks_svarut.domain.Organization
import org.springframework.data.repository.CrudRepository


interface OrganizationRepository extends CrudRepository<Organization, String>{
}