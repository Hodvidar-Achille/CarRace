package com.hodvidar.carrace.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;


public record RaceDto(@Nullable
                      Long id,
                      @NotBlank(message = "Location is mandatory")
                      String location,
                      @NotBlank(message = "Date is mandatory")
                      String date,
                      @NotNull(message = "Unique number is mandatory")
                      @Positive
                      Integer uniqueNumber,
                      @Size(min = 3, max = 100, message = "At least three participant is mandatory, and maximum is 100")
                      @Valid
                      List<ParticipantDto> participants) {
}
