package edu.bbte.idde.zdim1981.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "jpa_crudevents")
public class CrudEvent extends BaseEntity{
    private String crud;

    private Timestamp timestamp;
}
