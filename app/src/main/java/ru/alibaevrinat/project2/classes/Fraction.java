package ru.alibaevrinat.project2.classes;

public class Fraction {

    private int celoe;
    private int chislitel;
    private int znamenatel;

    public Fraction() {
        setCeloe();
        setZnamenatel();
        setChislitel(znamenatel);
    }

    public int getChislitel() {
        return chislitel;
    }
    public int getZnamenatel() {
        return znamenatel;
    }
    public int getCeloe() {
        return celoe;
    }

    public void setCeloe() {
        Double random = Math.random();
        int celoe = (int) Math.floor(random*100);
        this.celoe = celoe < 1 ? 1 : celoe;
    }

    public void setChislitel(int znam){
        Double random = Math.random();
        int chisl = (int) Math.floor(random*znam);
        this.chislitel = chisl < 1 ? 1 : chisl>znam ? znam-1 : chisl;
    }

    public void setZnamenatel () {
        Double random = Math.random();
        int znam = (int) Math.floor(random*10);
        this.znamenatel = znam <= 1 ? 2 : znam;
    }
}
