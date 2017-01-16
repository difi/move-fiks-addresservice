/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License")
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package main

import no.difi.ks_svarut.domain.Organization
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Import
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import repositories.OrganizationRepository

@SpringBootApplication
@RestController
@Import(value=Beans)
class AddressServiceApplication {

    @Autowired
    OrganizationRepository organizationRepository

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
        organizationRepository.save(organization)
    }


    static void main(String[] args) throws Exception {
        SpringApplication.run(AddressServiceApplication.class, args)
    }

}
