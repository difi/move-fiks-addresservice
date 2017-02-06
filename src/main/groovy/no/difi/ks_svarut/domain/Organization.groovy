package no.difi.ks_svarut.domain

import groovy.transform.Canonical

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Canonical
@Entity
class Organization {
    @Id
    String organizationId
    URL url

    @ManyToOne
    PemCertificate certificate
}
