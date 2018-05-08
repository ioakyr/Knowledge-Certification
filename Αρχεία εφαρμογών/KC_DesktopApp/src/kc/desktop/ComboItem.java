/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kc.desktop;

/**
 *
 * @author Γιάννης Κυρίτσης
 */
//Κλάση για jComboBox, σαν το option value στην html
//Δεν χρειάζεται τελικά
public class ComboItem {
    private final String value;
    private final String label;
    //Constructor
    public ComboItem(String value, String label) {
        this.value = value;
        this.label = label;
    }
    public String getId(String label) {
        return this.value;
    }
    public String getValue() {
        return this.value;
    }
    public String getLabel() {
        return this.label;
    }
    @Override
    public String toString() {
        return label;
    } 
}