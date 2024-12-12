package gcu.smapill_back.domain;

import gcu.smapill_back.domain.common.BaseEntity;
import gcu.smapill_back.domain.enums.TimeOfDay;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "prescription_id", nullable = false)
    private Prescription prescription;

    //약 명칭
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "schedule_date", nullable = false)
    private LocalDate scheduleDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "time_of_day")
    private TimeOfDay timeOfDay;

    @Column(name = "is_taken", nullable = false)
    private boolean isTaken;
}
