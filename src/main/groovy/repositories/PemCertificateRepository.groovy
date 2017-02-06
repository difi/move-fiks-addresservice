package repositories

import no.difi.ks_svarut.domain.PemCertificate
import org.springframework.data.repository.CrudRepository

interface PemCertificateRepository extends CrudRepository<PemCertificate, Integer> {

}