package edu.bbte.idde.zdim1981.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

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
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LastUpdatedAt lastUpdatedAt;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private LastViewedAt lastViewedAt;
    @Transient
    private Cpu cpu;
}
