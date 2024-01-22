package com.cognizant.upiapplication.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class response {
    private int code;
    private String msg;
    private Object data;
}
