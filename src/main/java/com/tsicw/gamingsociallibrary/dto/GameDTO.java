package com.tsicw.gamingsociallibrary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Component
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameDTO {

    private Long id;

    @NotEmpty(message = " game name is required")
    private String name;

    @NotEmpty(message = " is required")
    private String genre;
}
