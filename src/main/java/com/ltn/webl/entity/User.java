package com.ltn.webl.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {
 
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 private int id;
 
 @Column(name = "email")
 private String email;
 
 @Column(name = "firstname")
 private String firstname; 
 
 @Column(name = "lastname")
 private String lastname;
 
 @Column(name = "password")
 private String password;
 
 @Column(name = "active")
 private int active;
 
 //role_name temp
 private String r_name;
 
 @ManyToMany(cascade=CascadeType.ALL)
 @JoinTable(name="user_role", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="role_id"))
 private Set<Role> roles;

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public String getR_name() {
	return r_name;
}

public void setR_name(String r_name) {
	this.r_name = r_name;
}

public String getEmail() {
  return email;
 }

 public void setEmail(String email) {
  this.email = email;
 }

 public String getFirstname() {
  return firstname;
 }

 public void setFirstname(String firstname) {
  this.firstname = firstname;
 }

 public String getLastname() {
  return lastname;
 }

 public void setLastname(String lastname) {
  this.lastname = lastname;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 public int getActive() {
  return active;
 }

 public void setActive(int active) {
  this.active = active;
 }

 public Set<Role> getRoles() {
  return roles;
 }

 public void setRoles(Set<Role> roles) {
  this.roles = roles;
 }
}