package com.task.artemisdemo.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name="reports")
public class Message {
    @Id
    protected Integer id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "queue", nullable = false)
    private String queue;
}
