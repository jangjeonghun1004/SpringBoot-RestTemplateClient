package com.example.demo.dto;


public class MemberDTO {
    private String name;
    private String email;
    private String organization;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganization() {
        return this.organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return String.format(
                "MemberDTO{name='%s',email='%s', organization='%s'}",
                this.name,
                this.email,
                this.organization
        );
    }
}
