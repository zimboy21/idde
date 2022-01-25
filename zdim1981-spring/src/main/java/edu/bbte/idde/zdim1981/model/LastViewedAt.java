package edu.bbte.idde.zdim1981.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "jpa_views")
public class LastViewedAt extends BaseEntity{
    private LocalDate date;
    private LocalTime time;
}
