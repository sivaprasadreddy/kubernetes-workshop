package com.sivalabs.k8sbootdemo;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"version", "app", "hostName"})
public class AppInfo {
    private String version;
    private String app;
    private String hostName;
}
