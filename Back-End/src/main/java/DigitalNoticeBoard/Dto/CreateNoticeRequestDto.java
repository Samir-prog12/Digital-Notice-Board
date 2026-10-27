package DigitalNoticeBoard.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateNoticeRequestDto {

    private String title;
    private String description;
    private String category;
    private LocalDateTime expiryDate;
    private  LocalDateTime createdAt;
    private Boolean isImportant;
}
