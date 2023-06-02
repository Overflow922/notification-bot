package com.iyuriy.notification.common.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;


@Builder
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommandDto {

    @NotEmpty(message = "chatId should not be empty")
    private Long chatId;

    @NotEmpty(message = "text should not be empty")
    private String text;

}
