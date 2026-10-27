package DigitalNoticeBoard.Dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDto {

    private String username;

    private String password;

}
