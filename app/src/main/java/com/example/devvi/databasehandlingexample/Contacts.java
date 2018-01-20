package com.example.devvi.databasehandlingexample;

/**
 * Created by Devvi on 11/23/2017.
 */
public class Contacts {
    int  _id;
    String _name;
    String _phone_number;
    public Contacts(){}
    public Contacts(int id, String name, String phnumber){
        this._id=id;
        this._name=name;
        this._phone_number=phnumber;
    }
    public Contacts(String name,String phnumber){
        this._name=name;
        this._phone_number=phnumber;
    }


    public int get_id() {
        return _id;
    }

    public String get_phone_number() {
        return _phone_number;
    }

    public String get_name() {
        return _name;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_phone_number(String _phone_number) {
        this._phone_number = _phone_number;
    }
}
