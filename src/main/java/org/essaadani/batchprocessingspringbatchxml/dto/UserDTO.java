package org.essaadani.batchprocessingspringbatchxml.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@XmlRootElement(name="user")
public class UserDTO {
    private Long id;
    private String name;
}
