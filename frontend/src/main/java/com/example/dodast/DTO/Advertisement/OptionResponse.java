package com.example.dodast.DTO.Advertisement;

public class OptionResponse {
    
    private Long id;

    private String name;

    public OptionResponse() {}

    public OptionResponse(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        return name;
    }
}
