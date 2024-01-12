package com.apiPractise.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private String name;
    private String phone;

    @Override
    public String toString() {
        return ("Name: " + name + ", Phone: " + phone);

    }
}
