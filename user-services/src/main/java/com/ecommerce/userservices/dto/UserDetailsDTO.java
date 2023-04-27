package com.ecommerce.userservices.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Getter
@Setter
@Table(schema ="ecommerce", name = "user_details")
public class UserDetailsDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(name="user_details_id")
    private String userId;
    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;
    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "date_of_birth")
    @JsonFormat(pattern = "YYYY-MM-DD")
    private LocalDate dateofBirth;

    @Column(name ="address")
    private String address;

   @OneToOne(mappedBy = "userDetailsDTO",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private UserLoginDTO userLoginDTO;

   public UserDetailsDTO () { }
   public UserDetailsDTO(String userId, String firstName, String middleName, String lastName, LocalDate dateofBirth, String address) {
        this.userId = userId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateofBirth = dateofBirth;
        this.address = address;
    }
}
