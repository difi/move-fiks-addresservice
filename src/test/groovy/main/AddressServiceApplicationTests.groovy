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

import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.Test
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.DefaultResponseErrorHandler
import org.springframework.web.client.RestTemplate

import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit

import static org.junit.Assert.assertEquals
/**
 * Basic integration tests for service demo application.
 * 
 * @author Dave Syer
 */
public class AddressServiceApplicationTests {

	private static ConfigurableApplicationContext context

	@BeforeClass
	public static void start() throws Exception {
		Future<ConfigurableApplicationContext> future = Executors
				.newSingleThreadExecutor().submit(
						new Callable<ConfigurableApplicationContext>() {
							@Override
							public ConfigurableApplicationContext call() throws Exception {
								return SpringApplication
										.run(AddressServiceApplication.class)
							}
						})
		context = future.get(60, TimeUnit.SECONDS)
	}

	@AfterClass
	public static void stop() {
		if (context != null) {
			context.close()
		}
	}

	@Test
	void testErrorPageDirectAccess() throws Exception {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = getRestTemplate().getForEntity(
				"http://localhost:9100/error", Map.class)
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, entity.getStatusCode())
		@SuppressWarnings("unchecked")
		Map<String, Object> body = entity.getBody()
		assertEquals("None", body.get("error"))
		assertEquals(999, body.get("status"))
	}

	private String getPassword() {
		return context.getBean(SecurityProperties.class).getUser().getPassword()
	}

	private RestTemplate getRestTemplate() {

		RestTemplate restTemplate = new RestTemplate()
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
			@Override
			public void handleError(ClientHttpResponse response) throws IOException {
			}
		})
		restTemplate

	}

}
