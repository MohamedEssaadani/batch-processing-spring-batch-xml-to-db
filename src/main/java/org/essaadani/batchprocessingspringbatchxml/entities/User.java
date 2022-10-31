package org.essaadani.batchprocessingspringbatchxml.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name = "users_table")
public class User {
    @Id
    private Long id;
    private String name;
}
