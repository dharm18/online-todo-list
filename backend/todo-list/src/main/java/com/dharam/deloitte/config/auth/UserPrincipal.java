package com.dharam.deloitte.config.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dharam.deloitte.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;


public class UserPrincipal implements UserDetails {

  private static final long serialVersionUID = 5155720064139820502L;

  private final Long id;
  private String name;
  private final String username;
  @JsonIgnore
  private String email;
  private final String password;
  private final Collection<? extends GrantedAuthority> authorities;

  public UserPrincipal(Long id, String username, String password, String role) {
    this.id = id;
    this.username = username;
    this.password = password;

    List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority(role));

    this.authorities = authorities;
  }
  
  public UserPrincipal(Long id, String name, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
      this.id = id;
      this.name = name;
      this.username = username;
      this.email = email;
      this.password = password;
      this.authorities = authorities;
  }
  
  public static UserPrincipal create(User user) {
      List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
              new SimpleGrantedAuthority(role.getName().name())
      ).collect(Collectors.toList());

      return new UserPrincipal(
              user.getId(),
              user.getName(),
              user.getUsername(),
              user.getEmail(),
              user.getPassword(),
              authorities
      );
  }
  
  @JsonIgnore
  public Long getId() {
    return id;
  }

  public String getName() {
      return name;
  }
  public String getEmail() {
      return email;
  }
  
  @Override
  public String getUsername() {
    return username;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      UserPrincipal that = (UserPrincipal) o;
      return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {

      return Objects.hash(id);
  }
}


