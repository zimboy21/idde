package edu.bbte.idde.zdim1981.backend.model;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Motherboard extends BaseEntity {
    private String name;
    private Double price;
    private Integer fsb;
    private String bios;
    private Integer memory;
    private Long cpuId;
}
