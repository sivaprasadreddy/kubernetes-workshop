package com.sivalabs.k8sbootdemo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
@Setter
@Getter
public class ApplicationProperties {
    private String version;
    private String title;
}
