package ru.ruslan.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Set;

/**
 * The DTO for captcha
 * @author ruslan
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReCaptchaDto {
    private boolean success;

    @JsonAlias("error-codes")
    private Set<String> errorCodes;
}
