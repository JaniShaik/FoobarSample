package com.foobar.gireesam.model;

/**
 * Created by JANI SHAIK on 12/04/2020
 */

public class PaymentMethod {

    private String number;
    private int icon;
    private Boolean isSelected;

    public PaymentMethod(String number, int icon, Boolean isSelected) {
        this.number = number;
        this.icon = icon;
        this.isSelected = isSelected;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
