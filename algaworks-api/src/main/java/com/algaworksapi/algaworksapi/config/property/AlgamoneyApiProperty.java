package com.algaworksapi.algaworksapi.config.property;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("algamoney")//Deve ser adicionado uma dependencia extra no pom
public class AlgamoneyApiProperty {

    private String originPermitida = "http://localhost:8000";

    public String getOriginPermitida() {
        return originPermitida;
    }

    public void setOriginPermitida(String originPermitida) {
        this.originPermitida = originPermitida;
    }

    private final Seguranca seguranca = new Seguranca();

    public Seguranca getSeguranca() {
        return seguranca;
    }
//TODO:Sempre é bom separar o que faz parte de imfraestrutura e seguranca.
//Separar em partes as várias partes que componhe uma classe

    public static class Seguranca{


        private boolean enableHttps;

        public boolean isEnableHttps() {
            return enableHttps;
        }

        public void setEnableHttps(boolean enableHttps) {
            this.enableHttps = enableHttps;
        }
    }
}
