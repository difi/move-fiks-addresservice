package controller

import no.difi.ks_svarut.domain.Organization
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import repositories.OrganizationRepository
import repositories.PemCertificateRepository

@RestController
class OrganizationController {
    @Autowired
    OrganizationRepository organizationRepository

    @Autowired
    PemCertificateRepository certificateRepository

    @RequestMapping(path = "organization/{identifier}", method = RequestMethod.GET)
    ResponseEntity<Organization> getMapping(@PathVariable String identifier) {
        def organization = organizationRepository.findOne(identifier)
        if(!organization)
            return new ResponseEntity<Organization>(HttpStatus.NOT_FOUND)
        else {
            return new ResponseEntity<Organization>(organization, HttpStatus.OK)
        }
    }

    @RequestMapping(path = "organization/", method = RequestMethod.POST)
    insertMapping(@RequestBody Organization organization) {
        certificateRepository.findOne(organization.certificate.certificateId)
        organizationRepository.save(organization)
    }
}
