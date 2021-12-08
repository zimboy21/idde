package edu.bbte.idde.zdim1981.dto.outgoing;

import lombok.Data;

@Data
public class CpuDetailedDto {
    private Long id;
    private String name;
    private Double price;
    private Double clockSpeed;
    private Integer overClocking;
    private Integer coreCount;
}
