package com.example.e_commerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
public class User implements UserDetails {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    public String mobile;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Address> address = new ArrayList<>();

    @Embedded
    @ElementCollection
    @CollectionTable(name = "Payment_information",joinColumns = @JoinColumn(name = "user_id"))
    private List<PaymentInformation>paymentInformation = new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Rating>ratings = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy ="user",cascade = CascadeType.ALL)
    private List<Review>reviews = new ArrayList<>();

    private LocalDateTime createdAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address=" + address +
                ", paymentInformation=" + paymentInformation +
                ", ratings=" + ratings +
                ", reviews=" + reviews +
                ", createdAt=" + createdAt +
                '}';
    }
}
