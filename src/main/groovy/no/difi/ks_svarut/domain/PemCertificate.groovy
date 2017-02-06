package no.difi.ks_svarut.domain

import groovy.transform.Canonical

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Canonical
@Entity
class PemCertificate {
    @Id
    @GeneratedValue
    int certificateId

    String name
    String value
}
