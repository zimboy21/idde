package edu.bbte.idde.zdim1981.backend.model;

public class HardwareShopCpu extends BaseEntity {
    private int lcache;
    private double price;
    private double clockSpeed;
    private int overClocking;
    private int coreCount;

    public HardwareShopCpu(int lcache, double price, double clockSpeed, int overClocking, int coreCount) {
        this.lcache = lcache;
        this.price = price;
        this.clockSpeed = clockSpeed;
        this.overClocking = overClocking;
        this.coreCount = coreCount;
    }

    @Override
    public String toString() {
        return "Specs of CPU: "
                + "\n     L Cache: " + lcache
                + "\n     price: " + price
                + "\n     clock speed: " + clockSpeed
                + "\n     overclocking: " + overClocking
                + "\n     number of cores: " + coreCount;
    }

    public int getLCache() {
        return lcache;
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

    public void setlCache(int lcache) {
        this.lcache = lcache;
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
