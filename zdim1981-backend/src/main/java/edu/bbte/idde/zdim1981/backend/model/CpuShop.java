package edu.bbte.idde.zdim1981.backend.model;

public class CpuShop extends BaseEntity {
    private String name;
    private Double price;
    private Double clockSpeed;
    private Integer overClocking;
    private Integer coreCount;

    public CpuShop(String name, Double price, Double clockSpeed, Integer overClocking, Integer coreCount) {
        super();
        this.name = name;
        this.price = price;
        this.clockSpeed = clockSpeed;
        this.overClocking = overClocking;
        this.coreCount = coreCount;
    }

    public CpuShop() {
        super();
    }

    @Override
    public String toString() {
        return "Specs of CPU: "
                + "\n     L Cache: " + name
                + "\n     price: " + price
                + "\n     clock speed: " + clockSpeed
                + "\n     overclocking: " + overClocking
                + "\n     number of cores: " + coreCount;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Double getClockSpeed() {
        return clockSpeed;
    }

    public Integer getOverClocking() {
        return overClocking;
    }

    public Integer getCoreCount() {
        return coreCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setClockSpeed(Double clockSpeed) {
        this.clockSpeed = clockSpeed;
    }

    public void setOverClocking(Integer overClocking) {
        this.overClocking = overClocking;
    }

    public void setCoreCount(Integer coreCount) {
        this.coreCount = coreCount;
    }
}
