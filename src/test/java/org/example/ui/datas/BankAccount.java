package org.example.ui.datas;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankAccount {
   private String firstName;
   private String lastName;
   private String address;
   private String addressCity;
   private String addressState;
   private String zipCode;
   private String socialSecurityNumber;
   private String userName;
   private String passWord;
   private String confirmPassword;
}
