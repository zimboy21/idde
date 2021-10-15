package edu.bbte.idde.zdim1981.backend.model;

public class HardwareShopCpu extends BaseEntity {
    private String name;
    private double price;
    private double clockSpeed;
    private int overClocking;
    private int coreCount;

    public HardwareShopCpu(String name, double price, double clockSpeed, int overClocking, int coreCount) {
        this.name = name;
        this.price = price;
        this.clockSpeed = clockSpeed;
        this.overClocking = overClocking;
        this.coreCount = coreCount;
    }

    @Override
    public String toString() {
        return "Specs of " + name + ":"
                + "\n     price: " + price
                + "\n     clock speed: " + clockSpeed
                + "\n     overclocking: " + overClocking
                + "\n     number of cores: " + coreCount;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getClockSpeed() {
        return clockSpeed;
    }

    public int getOverClocking() {
        return overClocking;
    }

    public int getCoreCount() {
        return coreCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setClockSpeed(double clockSpeed) {
        this.clockSpeed = clockSpeed;
    }

    public void setOverClocking(int overClocking) {
        this.overClocking = overClocking;
    }

    public void setCoreCount(int coreCount) {
        this.coreCount = coreCount;
    }
}
