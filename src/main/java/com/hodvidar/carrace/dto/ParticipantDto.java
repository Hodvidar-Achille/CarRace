package com.hodvidar.carrace.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ParticipantDto(@Nullable
                             Long id,
                             @NotBlank(message = "Name is mandatory")
                             String name,
                             @NotNull(message = "Number is mandatory")
                             @Positive
                             Integer number) {
}
