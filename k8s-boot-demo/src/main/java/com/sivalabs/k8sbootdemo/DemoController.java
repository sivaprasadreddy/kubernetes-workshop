package com.sivalabs.k8sbootdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@Slf4j
public class DemoController {

    @Value("${app.version}")
    private String version;

    @GetMapping({"", "/api/info"})
    public Map<String, String> apiInfo()
    {
        log.info("Request for apiInfo at : {}", LocalDateTime.now());
        return Map.of("app", "K8S SpringBoot Demo",
            "hostName", getHostname(),
            "version", version);
    }

    @GetMapping("/api/terminate")
    public String terminate()
    {
        log.info("Request for terminate at : {}", LocalDateTime.now());
        System.exit(1); //give the developer a medal for writing this line in a web app
        return "I'll be BACK";
    }

    private String getHostname() {
        String hostName = "Unknown";
        try {
            InetAddress addr = InetAddress.getLocalHost();
            hostName = addr.getHostName();
        } catch (Exception e) {
            log.error("Error in getting hostname", e);
        }
        return hostName;
    }
}
