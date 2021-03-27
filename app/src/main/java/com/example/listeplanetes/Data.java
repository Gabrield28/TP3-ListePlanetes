package com.example.listeplanetes;

import java.util.ArrayList;

public class Data {
    private ArrayList<Planete> dataSet;

    // Starting Data
    private String[] nomPlanetes = {"Mercure", "Venus", "Terre", "Mars", "Jupiter", "Saturne", "Uranus", "Neptune", "Pluton"};
    private int[] taillePlanetes = {4900, 12000, 12800, 6800, 144000, 120000, 52000, 50000, 2300};

    public Data() {
        dataSet = new ArrayList<>();
        for (int i = 0; i < nomPlanetes.length; i++) {
            Planete planet = new Planete();
            planet.setNom(nomPlanetes[i]);
            planet.setTaille(taillePlanetes[i]);
            dataSet.add(planet);
        }
    }

    public ArrayList<Planete> getDataSet() {
        return dataSet;
    }
}
