package com.tecsup.petclinic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OwnerTO {

    private Integer id;
    private String nombres;
    private String address;
    private String telephone;
    private String email;

}
