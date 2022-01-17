package edu.bbte.idde.zdim1981.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "jpa_cpu")
public class Cpu extends BaseEntity {
    private String name;
    private Double price;
    private Double clockSpeed;
    private Integer overClocking;
    private Integer coreCount;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    @ToString.Exclude
    private Collection<Motherboard> motherboardCollection;
}
