package org.mentor.patterns.creational.builder;

import org.mentor.patterns.creational.builder.entities.*;

public class CakeBuilder implements BakeryBuilder {
    private Flavour flavour;
    private Glaze glaze;
    private Cream cream;
    private Candle candle;
    private int layers;

    @Override
    public void setFlavourType(Flavour flavour) {
        this.flavour = flavour;
    }

    @Override
    public void setGlazeType(Glaze glaze) {
        this.glaze = glaze;
    }

    @Override
    public void setCandle(Candle candle) {
        this.candle = candle;
    }

    @Override
    public void setCreamType(Cream cream) {
        this.cream = cream;
    }

    public void setNumberOfLayers(int layers) {
        this.layers = layers;
    }

    public Cake bakeCake(){
        return new Cake(candle,flavour,glaze,layers,cream);
    }
}
