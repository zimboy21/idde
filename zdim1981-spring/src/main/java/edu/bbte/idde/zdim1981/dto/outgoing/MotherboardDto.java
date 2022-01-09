package edu.bbte.idde.zdim1981.dto.outgoing;

import lombok.Data;

import java.io.Serializable;

@Data
public class MotherboardDto implements Serializable {
    private Long id;
    private String name;
    private Double price;
    private Integer fsb;
    private String bios;
    private Integer memory;
}
