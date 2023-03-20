package org.example.cinema.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GenericResponse<T> {
    private T body;
    private String errorMessage;
}
