package edu.bbte.idde.zdim1981.dto.incoming;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class MotherboardCreationDto implements Serializable {
    @NotNull
    private String name;
    @NotNull
    private Double price;
    @NotNull
    private Integer fsb;
    @NotNull
    private String bios;
    @NotNull
    private Integer memory;
}
