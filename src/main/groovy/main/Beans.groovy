package main

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories(basePackages = "repositories")
@EntityScan(basePackages = "no.difi.ks_svarut.domain")
@ComponentScan(basePackages = "controller")
@Configuration
class Beans {

    @Bean
    DataSourceAutoConfiguration dataSource() {
       return new DataSourceAutoConfiguration()
    }

}
