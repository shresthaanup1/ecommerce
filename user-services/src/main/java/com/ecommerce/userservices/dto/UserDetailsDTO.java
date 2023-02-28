package com.ecommerce.userservices.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(schema ="ecommerce", name = "user_details")
public class UserDetailsDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="user_details_id")
    private String userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    @JsonFormat(pattern = "YYYY-MM-DD")
    private LocalDate dateofBirth;

    @Column(name ="address")
    private String address;

   @OneToOne(mappedBy = "userDetailsDTO",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private UserLoginDTO userLoginDTO;

    public UserDetailsDTO() {}

    public UserDetailsDTO(String userId, String firstName, String middleName, String lastName, LocalDate dateofBirth, String address) {
        this.userId = userId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateofBirth = dateofBirth;
        this.address = address;
    }

   /* public UserDetailsDTO(Optional<UserDetailsDTO> optionalUserDetailsDTO, String userId) {
    }
*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId() {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(LocalDate dateofBirth) {
        this.dateofBirth = dateofBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
