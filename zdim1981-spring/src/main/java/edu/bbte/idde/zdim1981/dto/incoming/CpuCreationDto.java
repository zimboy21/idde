package edu.bbte.idde.zdim1981.dto.incoming;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class CpuCreationDto {
    @NotNull
    @Size(max = 100)
    private String name;
    @Positive
    private Double price;
    @Positive
    private Double clockSpeed;
    @Positive
    private Integer overClocking;
    @Positive
    private Integer coreCount;
}
