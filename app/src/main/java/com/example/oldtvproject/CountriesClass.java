package com.example.oldtvproject;

import java.util.ArrayList;

public class CountriesClass {
    private final int level_Count;
    private final ArrayList<String[]> countries;
    private final ArrayList<Integer> times;
    private final ArrayList<double[]> coefs;
    {
        countries = new ArrayList<String[]>();
        times = new ArrayList<Integer>();
        coefs = new ArrayList<double[]>();
        countries.add(new String[]{"Казахстан", "Киев", "Сарай", "Пита", "Рим", "Москва", "Вашингтон", "Томск", "Воробей", "Эйр", "Винница"});
        times.add(5);
        coefs.add(new double[]{0.5, 0., 0., 0.});
        // countries.set(1, new String[]{});
        level_Count = countries.size();
    }
    public int[] getTimes() {
        int[] timess = new int[level_Count];
        for (int i = 0; i < level_Count; i++) {
            timess[i] = times.get(i);
        }
        return timess;
    }
    public String[] getCountries(int level) {
        if (level < level_Count)
            return countries.get(level);
        return new String[]{"null"};
    }
    public double[] getCoefs(int level) {
        if (level < level_Count)
            return coefs.get(level);
        return new double[]{0., 0., 0.,0.};
    }
    public int[] getSizes() {
        int[] sizes = new int[level_Count];
        for (int i = 0; i < level_Count; i++) {
            sizes[i] = countries.get(i).length;
        }
        return sizes;
    }
    public int getLevel_Count() {return level_Count;}
}
