package edu.bbte.idde.zdim1981.backend.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HardwareShopCpu extends BaseEntity {
    private String name;
    private double price;
    private double clockSpeed;
    private int overClocking;
    private int coreCount;

    public static final Logger LOG = LoggerFactory.getLogger(HardwareShopCpu.class);

    public HardwareShopCpu(String name, double price, double clockSpeed, int overClocking, int coreCount) {
        this.name = name;
        this.price = price;
        this.clockSpeed = clockSpeed;
        this.overClocking = overClocking;
        this.coreCount = coreCount;
    }

    @Override
    public String toString() {
        LOG.info("toString() called.");
        return "Specs of " + name + ":"
                + "\n     price: " + price
                + "\n     clock speed: " + clockSpeed
                + "\n     overclocking: " + overClocking
                + "\n     number of cores: " + coreCount;
    }

    public String getName() {
        LOG.info("getName() called.");
        return name;
    }

    public double getPrice() {
        LOG.info("getPrice() called.");
        return price;
    }

    public double getClockSpeed() {
        LOG.info("getClockSpeed() called.");
        return clockSpeed;
    }

    public int getOverClocking() {
        LOG.info("getOverClocking() called.");
        return overClocking;
    }

    public int getCoreCount() {
        LOG.info("getCoreCount() called.");
        return coreCount;
    }

    public void setName(String name) {
        LOG.info("setName() called.");
        this.name = name;
    }

    public void setPrice(double price) {
        LOG.info("setPrice() called.");
        this.price = price;
    }

    public void setClockSpeed(double clockSpeed) {
        LOG.info("setClockSpeed() called.");
        this.clockSpeed = clockSpeed;
    }

    public void setOverClocking(int overClocking) {
        LOG.info("setOverClocking() called.");
        this.overClocking = overClocking;
    }

    public void setCoreCount(int coreCount) {
        LOG.info("setCoreCount() called.");
        this.coreCount = coreCount;
    }
}
