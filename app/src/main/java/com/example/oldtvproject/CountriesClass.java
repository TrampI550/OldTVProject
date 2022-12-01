package com.example.oldtvproject;

import java.util.ArrayList;

public class CountriesClass {
    private final int level_Count;
    private final ArrayList<String[]> countries;
    private final ArrayList<Integer> times;
    private final ArrayList<double[]> coefs;
    private final ArrayList<double[]> buffs;
    {
        countries = new ArrayList<String[]>();
        times = new ArrayList<Integer>();
        coefs = new ArrayList<double[]>();
        buffs = new ArrayList<double[]>();
        countries.add(new String[]{"Сирия", "Эритрея", "Куба", "Венесуэла", "Сербия", "Армения", "Таджикистан", "Киргизстан", "Индия", "Преднестровье", "Турция?", "Беларусь", "КНДР"});
        times.add(37);
        buffs.add(new double[]{2.5});
        coefs.add(new double[]{0.5, 0., 0., 0.});
        countries.add(new String[]{"Латвия", "Литва", "Эстония", "Молдавия", "Грузия", "Казахстан?", "Япония", "Франция", "Великобритания", "Германия", "Канада", "США"});
        times.add(15);
        buffs.add(new double[]{1.});
        coefs.add(new double[]{0.5, 0.2, 0.2, 0.2});
        countries.add(new String[]{"Алжир", "Буэа", "Луанда", "Порто-Ново", "Габороне",
                "Уагадугу", "Гитега", "Либревиль", "Банжул", "Аккра", "Конакри", "Бисау", "Джибути"
                , "Каир", "Лусака", "Хараре", "Прая", "Яунде", "Найроби", "Морони", "Киншаса"
                , "Браззавиль", "Ямусукро", "Масеру", "Монровия", "Триполи", "Порт-Луи", "Нуакшот"
                , "Антананариву", "Фуншал", "Мамудзу", "Лилонгве", "Бамако", "Рабат", "Мапуту"});
        times.add(15);
        buffs.add(new double[]{0.5});
        coefs.add(new double[]{0.5, 0.5, 0.5, 0.5});
        level_Count = countries.size();
    }
    public double getBuff(int level) {
        return buffs.get(level)[0];
    }
    public int getTime(int level) {
        return times.get(level);
    }
    public String[] getCountries(int level) {
        return countries.get(level);
    }
    public double[] getCoefs(int level) {
        return coefs.get(level);
    }
    public int getSize(int level) {
        return countries.get(level).length;
    }
    public int getLevel_Count() {
        System.out.println(level_Count);
        return level_Count;}
}
