package edu.bbte.idde.zdim1981.backend.config;

import lombok.*;

@Setter
@Getter
@ToString
public class Config {
    private String daoType;
    private String daoUrl;
    private String daoUserName;
    private String daoPassword;
    private Integer daoPoolSize;
    private String daoDriverClassName;
}
