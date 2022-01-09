package edu.bbte.idde.zdim1981.model;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "jpa_motherboard")
public class Motherboard extends BaseEntity {
    private String name;
    private Double price;
    private Integer fsb;
    private String bios;
    private Integer memory;
    @Transient
    private Cpu cpu;
}
