package com.social.backend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@lombok.Builder
public class ErrorDetails {
    private Date timestamp;
    private String message;


}