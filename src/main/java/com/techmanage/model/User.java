package com.techmanage.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String fullName;

    @Email @NotBlank
    private String email;

    @Pattern(regexp = "^\\+\\d{1,3}\\s\\d{2}\\s\\d{4,5}-\\d{4}$")
    private String phone;

    @Past(message = "Data de nascimento deve estar no passado")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private UserType userType;
}
