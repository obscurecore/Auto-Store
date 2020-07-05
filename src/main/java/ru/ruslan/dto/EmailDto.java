package ru.ruslan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The DTO to send email: for confirmation account
 * @author ruslan
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {
    private String username;
    private String to;
    private String templateName;
    private String secret;
}