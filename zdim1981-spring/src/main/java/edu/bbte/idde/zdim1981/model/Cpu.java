package edu.bbte.idde.zdim1981.model;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class Cpu extends BaseEntity {
    private String name;
    private Double price;
    private Double clockSpeed;
    private Integer overClocking;
    private Integer coreCount;
}
