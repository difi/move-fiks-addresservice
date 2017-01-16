package no.difi.ks_svarut.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Organization {
    @Id
    String organizationId
    URL url
    String certificate
}
