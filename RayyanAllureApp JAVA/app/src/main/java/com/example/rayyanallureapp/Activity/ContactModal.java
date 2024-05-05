package com.example.rayyanallureapp.Activity;

public class ContactModal {
    int img;
    String name, number;

    ContactModal(int img, String name, String number) {
        this.img = img;
        this.name = name;
        this.number = number;
    }

    ContactModal(String name, String number) {
        this.name = name;
        this.number = number;
    }
}
