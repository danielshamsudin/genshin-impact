package msd.materials;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TalentBook {
    private ArrayList<String> characters;
    private ArrayList<String> availability;
    private String source;

    @JsonIgnore
    private ArrayList<Object> items;
    
    public TalentBook(){}

    public TalentBook(@JsonProperty("characters") ArrayList<String> characters,
                        @JsonProperty("availability") ArrayList<String> availability,
                        @JsonProperty("source") String source,
                        @JsonProperty("items") ArrayList<Object> items){

        this.characters = characters;
        this.availability = availability;
        this.source = source;
        this.items = items;
    }

    public ArrayList<String> getCharacters() { return this.characters; }
    public void setCharacters(ArrayList<String> characters){ this.characters = characters;}
    
    public ArrayList<String> getAvailability() { return this.availability; }
    public void setAvailability(ArrayList<String> availability){ this.availability = availability;}

    public String getSource() { return this.source; }
    public void setSource (String source) { this.source = source; }

    public ArrayList<Object> getItems() { return this.items; }
    public void setItems(ArrayList<Object> items){ this.items = items;}
}
