package pl.lilianakrol.quizzically.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationEmail implements Serializable {
    private String subject;
    private String recipient;
    private String body;
}

