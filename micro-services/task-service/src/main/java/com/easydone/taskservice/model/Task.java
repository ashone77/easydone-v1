package com.easydone.taskservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "Task")
@Table(name = "task")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task {

    @Id
    @SequenceGenerator(
            name = "task_sequence",
            sequenceName = "task_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "task_sequence"
    )
    private Long taskId;
    @Column(nullable = false)
    private String taskTitle;
    @Column(nullable = false)
    private String taskDescr;
    @Column(nullable = false)
    private String taskLocation;
    @Column(nullable = false)
    private String taskTime;
    @Column(nullable = false)
    private Long payment;
    @Column(nullable = false)
    private Long userId;

}
