package DigitalNoticeBoard.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoticeDto {

    private Long id;

    private String title;

    private String description;

    private String category;

    private LocalDateTime createdAt;

    private LocalDateTime expiryDate;

    private Boolean isImportant;
}
