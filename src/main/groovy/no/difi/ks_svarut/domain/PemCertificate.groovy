package no.difi.ks_svarut.domain

import groovy.transform.Canonical

import javax.persistence.*

@Canonical
@Entity
class PemCertificate {
    @Id
    @GeneratedValue
    int certificateId

    String name
    @Lob
    @Column(length=2048)
    String value
}
