package com.sivalabs.k8sbootdemo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@Slf4j
public class DemoController {
    private final ApplicationProperties properties;

    @GetMapping({"", "/api/info"})
    public AppInfo apiInfo()
    {
        log.info("Request for apiInfo at : {}", LocalDateTime.now());
        return new AppInfo(properties.getVersion(), properties.getTitle(), getHostname());
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
