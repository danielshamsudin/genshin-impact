package msd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import msd.materials.*;

public class MaterialFilter {
    
    private String allTalentBookJson;
    private String allWeaponAscensionJson;

    private final GenshinAPI genshinAPI = new GenshinAPI();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private HashMap<String,TalentBook> allTalentBooks;
    private HashMap<String,WeaponAscension> allWeaponAscensions;

    // Given key Day, get TalentBook (key) list
    private HashMap<String,ArrayList<String>> dayToTalentBook = new HashMap<>();
    // Given key Day, get TalentBook list
    private HashMap<String,ArrayList<String>> dayToWeaponAscension = new HashMap<>();

    private HashMap<String,ArrayList<String>> dayToCharacters = new HashMap<>();
    private HashMap<String,ArrayList<String>> dayToWeapons = new HashMap<>();

    private ArrayList<String> characterList;

    public MaterialFilter(){

        this.allTalentBookJson = genshinAPI.fetchTalentBook();
        this.allWeaponAscensionJson = genshinAPI.fetchWeaponAscension();

        // System.out.println(allTalentBookJson);
		try {
            this.allTalentBooks = objectMapper.readValue(allTalentBookJson, new TypeReference<HashMap<String,TalentBook>>() {});
            this.allWeaponAscensions = objectMapper.readValue(allWeaponAscensionJson, new TypeReference<HashMap<String,WeaponAscension>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }

        initTalentBooksOnDay();
        initWeaponAscensionOnDay();

        initCharactersOnDay();
        initWeaponsOnDay();

        initCharactersList();
        // test();
    }

    public void test(){
        
        ArrayList<String> allTalentBookKeys = new ArrayList<>(this.allTalentBooks.keySet());

        String firstKey = allTalentBookKeys.get(0);
        TalentBook tb = this.allTalentBooks.get(firstKey);//.toString();
        
        System.out.println(tb.getCharacters().toString());

        ArrayList<String> allWeaponAscensionKeys = new ArrayList<>(this.allWeaponAscensions.keySet());

        String firstKey2 = allWeaponAscensionKeys.get(0);
        WeaponAscension wa = this.allWeaponAscensions.get(firstKey2);//.toString();
        
        System.out.println(wa.getWeapons().toString());
    }


    public void initTalentBooksOnDay(){
        final ArrayList<String> days = new ArrayList<>(Arrays.asList("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"));

        for (String day : days){
            ArrayList<String> allTalentBookOnDay = new ArrayList<>();

            ArrayList<String> allKeys = new ArrayList<>(this.allTalentBooks.keySet());
            for (String key : allKeys){
                TalentBook tb = this.allTalentBooks.get(key);

                ArrayList<String> availability = tb.getAvailability();
                if (availability.contains(day)){
                    allTalentBookOnDay.add(key);
                }
            }
            dayToTalentBook.put(day, allTalentBookOnDay);
        }

        // System.out.println(dayToTalentBook.toString());
    }

    public void initWeaponAscensionOnDay(){

        final ArrayList<String> days = new ArrayList<>(Arrays.asList("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"));

        for (String day : days){
            ArrayList<String> allWeaponAscensionOnDay = new ArrayList<>();

            ArrayList<String> allKeys = new ArrayList<>(this.allWeaponAscensions.keySet());
            for (String key : allKeys){
                WeaponAscension wa = this.allWeaponAscensions.get(key);

                ArrayList<String> availability = wa.getAvailability();

                if (availability.contains(day)){
                    allWeaponAscensionOnDay.add(key);
                }
            }
            this.dayToWeaponAscension.put(day, allWeaponAscensionOnDay);
        }

        // System.out.println(dayToWeaponAscension.toString());
    }

    public ArrayList<String> getMaterialListOnDay(String day){
        ArrayList<String> talentBooks = this.dayToTalentBook.get(day);
        ArrayList<String> weaponAscensions = this.dayToWeaponAscension.get(day);

        ArrayList<String> allMaterials = talentBooks;
        allMaterials.addAll(weaponAscensions);
        return allMaterials;
    }

    // end goal, given list of characters and weapons get which domain they can do on that day
    // get characters list on day


    public void initCharactersOnDay(){
        final ArrayList<String> days = new ArrayList<>(Arrays.asList("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"));

        for (String day : days){
            ArrayList<String> allCharactersOnDay = new ArrayList<>();

            ArrayList<String> allKeys = new ArrayList<>(this.allTalentBooks.keySet());
            for (String key : allKeys){
                TalentBook tb = this.allTalentBooks.get(key);

                ArrayList<String> availability = tb.getAvailability();
                ArrayList<String> characters = tb.getCharacters();

                if (availability.contains(day)){
                    allCharactersOnDay.addAll(characters);
                }
            }

            Set<String> set = new HashSet<>(allCharactersOnDay);
            allCharactersOnDay.clear();
            allCharactersOnDay.addAll(set);
            this.dayToCharacters.put(day,allCharactersOnDay);
        }

        // System.out.println(dayToCharacters.toString());
    }

    public void initWeaponsOnDay(){

        final ArrayList<String> days = new ArrayList<>(Arrays.asList("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"));

        for (String day : days){
            ArrayList<String> allWeaponsOnDay = new ArrayList<>();

            ArrayList<String> allKeys = new ArrayList<>(this.allWeaponAscensions.keySet());
            for (String key : allKeys){
                WeaponAscension wa = this.allWeaponAscensions.get(key);

                ArrayList<String> availability = wa.getAvailability();
                ArrayList<String> weapons = wa.getWeapons();
                if (availability.contains(day)){
                    allWeaponsOnDay.addAll(weapons);
                }
            }
            this.dayToWeapons.put(day, allWeaponsOnDay);
        }

        // System.out.println(dayToWeapons.toString());
    }

    public ArrayList<String> getCharactersOnDay(String day){
        return this.dayToCharacters.get(day);
    }

    public ArrayList<String> getWeaponsOnDay(String day){
        return this.dayToWeapons.get(day);
    }

    private void initCharactersList(){
        String charList = genshinAPI.getCharactersList();

        try {
            this.characterList = objectMapper.readValue(charList, new TypeReference<ArrayList<String>>() {});
        } catch (Exception e) {
            System.out.println(e.toString());
        }
            
    }
    
    public ArrayList<String> getCharactersList(){
        return this.characterList;
    }
}


