package com.uberApplication.uber.entities;

import java.util.Set;

import com.uberApplication.uber.entities.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_user")  // we make this user bcz user has a predefine a field in postgres so we don't want to confuse
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
   private Long id;

   private String name;

   @Column(unique = true)
   private String email;

   private String password;

   @ElementCollection(fetch=FetchType.LAZY)
   @Enumerated(EnumType.STRING)
   private Set<Role> roles;

}
