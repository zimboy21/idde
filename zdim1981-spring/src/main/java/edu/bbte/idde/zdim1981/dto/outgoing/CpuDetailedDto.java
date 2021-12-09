package edu.bbte.idde.zdim1981.dto.outgoing;

import lombok.Data;

import java.io.Serializable;

@Data
public class CpuDetailedDto implements Serializable {
    private Long id;
    private String name;
    private Double price;
    private Double clockSpeed;
    private Integer overClocking;
    private Integer coreCount;
}
