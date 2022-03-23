package com.example.penilaian.models;

public class MahasiswaModel {
    private final String nama;
    private final String mk;
    private final int nim;
    private final float tugas;
    private final float quiz;
    private final float uts;
    private final float uas;
    private String charScore;
    private float numberScore;

    public MahasiswaModel(String nama, String mk, int nim, float tugas, float quiz, float uts, float uas) {
        this.nama = nama;
        this.mk = mk;
        this.nim = nim;
        this.tugas = tugas;
        this.quiz = quiz;
        this.uts = uts;
        this.uas = uas;
    }

    //method
    public String getNama() {
        return nama;
    }
    public String getMk() {
        return mk;
    }
    public int getNim() {
        return nim;
    }

    //operasi sistem penilaian dengan uas 40%, uts 35%, tugas 20%, dan quiz 5%
    public float getNumberScore() {
        numberScore = (float) ((uas*0.4)+(uts*0.35)+(tugas*0.2)+(quiz*0.05));
        return numberScore;
    }

    //Logika Untuk mendapatkan Nilai A,B,C,D,.....
    public String getCharScore() {
        float a = getNumberScore();

        if(a>=92 && a<=100){
            charScore =  "A";
        }else if(a>=86 && a<=91){
            charScore =  "A-";
        }else if(a>=81 && a<=85){
            charScore =  "B+";
        }else if(a>=76 && a<=80){
            charScore =  "B";
        }else if(a>=71 && a<=75){
            charScore = "B-";
        }else if(a>=66 && a<=70){
            charScore = "C+";
        }else if(a>=60 && a<=65){
            charScore = "C";
        }else if(a>=55 && a<=59){
            charScore = "D";
        }else{
            charScore = "E";
        }
        return charScore;
    }
}