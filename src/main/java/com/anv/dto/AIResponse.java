package com.anv.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AIResponse {

    private boolean success;
    private String message;
    private Object data;
    private LocalDateTime timestamp;

    public AIResponse(String response) {
        this.success = true;
        this.message = "Response generated successfully";
        this.data = response;
        this.timestamp = LocalDateTime.now();
    }
}
