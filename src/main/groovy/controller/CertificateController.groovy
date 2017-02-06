package controller

import no.difi.ks_svarut.domain.PemCertificate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import repositories.PemCertificateRepository

@RestController
class CertificateController {
    @Autowired
    PemCertificateRepository repository

    @RequestMapping(path="/certificates")
    List<PemCertificate> getPermCertificates() {
        return repository.findAll()
    }

    @RequestMapping(path = "/certificate/{id}")
    PemCertificate getPermCertificate(@PathVariable int id) {
        def certificate = repository.findOne(id)
        if (!certificate) {
            return new ResponseEntity<PemCertificate>(HttpStatus.NOT_FOUND)
        } else {
            return new ResponseEntity<PemCertificate>(certificate, HttpStatus.OK)
        }
    }

    @RequestMapping(path = "/certificate/", method = RequestMethod.POST)
    void createPemCertificate(@RequestBody PemCertificate certificate) {
        repository.save(certificate)
    }

    @RequestMapping(path = "/certificate/{id}", method = RequestMethod.DELETE)
    void deletePemCertificate(@PathVariable int id) {
        repository.delete(id)
    }

}
