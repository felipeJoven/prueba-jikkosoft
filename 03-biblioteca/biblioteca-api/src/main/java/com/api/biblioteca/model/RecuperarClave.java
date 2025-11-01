package com.api.biblioteca.model;

import com.api.biblioteca.security.model.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * Entidad que representa a una recuperación de contraseña de un usuario.
 * Contiene codigo otp y tiempo de expiración del código.
 * <p>
 *
 * Esta entidad se relaciona uno a uno con Usuario para que cada usuario tenga su proceso.
 *
 * @author Felipe Joven
 * @version 1.0
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecuperarClave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer otp;

    @Column(nullable = false)
    private Date tiempoExpiracion;

    @JsonIgnore
    @OneToOne
    private Usuario usuario;
}
