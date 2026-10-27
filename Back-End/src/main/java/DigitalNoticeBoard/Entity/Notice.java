package DigitalNoticeBoard.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name  = "notices")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;


    private String category;

    private LocalDateTime createdAt;


    private LocalDateTime ExpiryDate;

    private Boolean isImportant;



}
