package com.BudgetPlanner.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class WebUser {


    @NotNull(message = "this field is required!")
    @Size(min=1, message="this field is required")
    private String username;
    @NotNull(message = "this field is required!")
    @Size(min=1, message="this field is required")
    private String password;
    @NotNull(message = "this field is required!")
    @Size(min=1, message="this field is required")
    private String firstName;
    @NotNull(message = "this field is required!")
    @Size(min=1, message="this field is required")
    private String lastName;
    @NotNull(message = "this field is required!")
    @Size(min=1, message="this field is required")
    private String email;
}
