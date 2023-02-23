package com.example.demo;

import lombok.Value;

@Value(staticConstructor = "of")
public class DemoDTO {

    private String name;
    private Integer age;
}
