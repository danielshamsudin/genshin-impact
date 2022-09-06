package msd.materials;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WeaponAscension {
    private ArrayList<String> weapons;
    private ArrayList<String> availability;
    private String source;

    @JsonIgnore
    private ArrayList<Object> items;
    
    public WeaponAscension(@JsonProperty("weapons") ArrayList<String> weapons,
                        @JsonProperty("availability") ArrayList<String> availability,
                        @JsonProperty("source") String source,
                        @JsonProperty("items") ArrayList<Object> items){

        this.weapons = weapons;
        this.availability = availability;
        this.source = source;
        this.items = items;
    }

    public ArrayList<String> getWeapons() { return this.weapons; }
    public void setWeapons(ArrayList<String> weapons){ this.weapons = weapons;}
    
    public ArrayList<String> getAvailability() { return this.availability; }
    public void setAvailability(ArrayList<String> availability){ this.availability = availability;}

    public String getSource() { return this.source; }
    public void setSource (String source) { this.source = source; }

    public ArrayList<Object> getItems() { return this.items; }
    public void setItems(ArrayList<Object> items){ this.items = items;}
}

