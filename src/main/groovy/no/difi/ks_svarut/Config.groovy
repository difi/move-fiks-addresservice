package no.difi.ks_svarut

import lombok.Data
import org.springframework.boot.context.properties.ConfigurationProperties

@Data
@ConfigurationProperties(prefix = "difi.move.ks_svarut")
class Config {
    
}
