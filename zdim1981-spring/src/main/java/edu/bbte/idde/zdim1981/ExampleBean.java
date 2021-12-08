package edu.bbte.idde.zdim1981;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ExampleBean {
    private static final Logger LOG = LoggerFactory.getLogger(ExampleBean.class);

    @PostConstruct
    public void postConstruct() {
        LOG.info("PostConstruct function executed!");
    }
}
