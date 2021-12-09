package edu.bbte.idde.zdim1981.dto.incoming;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class CpuCreationDto implements Serializable {
    @NotNull
    @Size(max = 100)
    private String name;
    @Positive
    @NotNull
    private Double price;
    @Positive
    @NotNull
    private Double clockSpeed;
    @Positive
    @NotNull
    private Integer overClocking;
    @Positive
    @NotNull
    private Integer coreCount;
}
