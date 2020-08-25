package com.example.eosbasicapp;

import java.util.ArrayList;

public class DummyData {

    public static ArrayList<Contact> contacts= new ArrayList<>();

    static {
        contacts.add(new Contact("곽용우","010-3744-0844","kkolbuyw@gmail.com"));
        contacts.add(new Contact("윤무원","010-3744-0844","sample@gmail.com"));
        contacts.add(new Contact("Name","010-1234-5678","email@gmail.com"));
    }
}
