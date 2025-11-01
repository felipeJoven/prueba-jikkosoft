package com.api.biblioteca.security.model;

import com.api.biblioteca.model.Base;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Entidad que representa a un usuario en el sistema.
 * Contiene información básica como el nombre para el usuario, correo y contraseña.
 *
 * @author Felipe Joven
 * @version 1.0
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario extends Base implements UserDetails {

    @Column(name = "nombre_completo", nullable = false)
    private String nombre;

    @Column(name = "nombre_usuario", unique = true, nullable = false)
    private String usuario;

    @Column(unique = true, nullable = false)
    private String correo;

    @Column(nullable = false)
    private String clave;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.getRol()));
    }

    @Override
    public String getPassword() {
        return this.clave;
    }

    @Override
    public String getUsername() {
        return this.correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
