package com.example.demo.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mes-configs")
@RefreshScope
public class ApplicationPropertiesConfiguration {
    // correspond à la propriété « mes-configs.limitDeCommandes » dans le fichier de configuration du MS
    private int limitDeCommandes;

    public int getLimitDeCommandes() {
        return limitDeCommandes;
    }

    public void setLimitDeCommandes(int limitDeCommandes) {
        this.limitDeCommandes = limitDeCommandes;
    }
}
