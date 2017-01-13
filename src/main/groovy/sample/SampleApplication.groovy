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

package sample

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import javax.annotation.PostConstruct

@SpringBootApplication
@RestController
class SampleApplication {
    Map<String, Map> mappings = new HashMap<>()

    @PostConstruct
    init() {
        mappings.put("123456789", [url: "http://localhost:12345", certificate: "123123123"])
    }

    @RequestMapping(path = "organization/{identifier}", method = RequestMethod.GET)
    getMapping(@PathVariable String identifier) {
        mappings.get(identifier)
    }

    @RequestMapping(path = "organization/{identifier}", method = RequestMethod.POST)
    insertMapping(@PathVariable String identifier, @RequestBody Map<String, String> values) {
        mappings.put(identifier, values)
    }


    static void main(String[] args) throws Exception {
        SpringApplication.run(SampleApplication.class, args)
    }

}
