package com.itmo.utils;

public class Client {
    private String name;
    private String surName;
    private String address;
    private String passport;

    public Client(String name, String surName) {
        this.name = name;
        this.surName = surName;
        this.passport = "";
        this.address = "";
    }

    public Client(String name, String surName, String address, String passport) {
        this.name = name;
        this.surName = surName;
        this.address = address;
        this.passport = passport;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getAddress() {
        return address;
    }

    public String getPassport() {
        return passport;
    }

    @Override
    public String toString() {
        return name + " " + surName + ", "
                + address + ", "
                + passport;
    }
}
