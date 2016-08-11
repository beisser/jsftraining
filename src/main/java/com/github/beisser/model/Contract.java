package com.github.beisser.model;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Created by Nico on 11.08.2016.
 */
@Named
@RequestScoped
public class Contract {

    private String contract = "contract1";

    public String getContract() {
        return contract;
    }

}
