package controller

import no.difi.ks_svarut.domain.PemCertificate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
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

    @PostMapping(path = "/certificate/")
    void createPemCertificate(@RequestParam String certificateName, @RequestParam("file") MultipartFile file) {
        PemCertificate certificate = new PemCertificate(name:certificateName, value:new String(file.bytes))
        repository.save(certificate)
    }

    @RequestMapping(path = "/certificate/{id}", method = RequestMethod.DELETE)
    void deletePemCertificate(@PathVariable int id) {
        repository.delete(id)
    }

}
