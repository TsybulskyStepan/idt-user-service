package com.idt.assignment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

/**
 * User transfer object
 */
@Data
@Builder
@JsonInclude(value = NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank
    private String id;
    @Length(max = 255)
    private String name;

}
