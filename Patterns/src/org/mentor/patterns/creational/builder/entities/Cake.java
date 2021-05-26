package org.mentor.patterns.creational.builder.entities;

public class Cake {
    private Candle candle;
    private Flavour flavour;
    private Glaze glaze;
    private int layers;
    private Cream cream;

    public Cake(Candle candle, Flavour flavour, Glaze glaze, int layers, Cream cream) {
        this.candle = candle;
        this.flavour = flavour;
        this.glaze = glaze;
        this.layers = layers;
        this.cream = cream;
    }

    public Flavour getFlavour() {
        return flavour;
    }

    public void setFlavour(Flavour flavour) {
        this.flavour = flavour;
    }

    public Glaze getGlaze() {
        return glaze;
    }

    public void setGlaze(Glaze glaze) {
        this.glaze = glaze;
    }

    public int getLayers() {
        return layers;
    }

    public void setLayers(int layers) {
        this.layers = layers;
    }

    public Cream getCream() {
        return cream;
    }

    public void setCream(Cream cream) {
        this.cream = cream;
    }

    @Override
    public String toString() {
        return "Cake{" +
                "candle=" + candle.toString() +
                ", flavour=" + flavour.toString() +
                ", glaze=" + glaze.toString() +
                ", layers=" + layers +
                ", cream=" + cream.toString() +
                '}';
    }
}
