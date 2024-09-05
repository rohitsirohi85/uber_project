package com.uberApplication.uber.entities;

import java.util.Set;

import com.uberApplication.uber.entities.enums.Role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "app_user" , indexes = {
        @Index(name = "idx_user_email" , columnList = "email")
})  // we make this user bcz user has a predefine a field in postgres, so we don't want to confuse
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
