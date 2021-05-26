package org.mentor.patterns.creational.builder;

import org.mentor.patterns.creational.builder.entities.Candle;
import org.mentor.patterns.creational.builder.entities.Cream;
import org.mentor.patterns.creational.builder.entities.Flavour;
import org.mentor.patterns.creational.builder.entities.Glaze;

public class Conveyor {
/*    private BakeryBuilder builder;

    public void setBuilder(BakeryBuilder builder) {
        this.builder = builder;
    }*/

    public void createSimpleCake(CakeBuilder cakeBuilder){
        cakeBuilder.setNumberOfLayers(1);
        cakeBuilder.setCreamType(new Cream("Ванильный"));
        cakeBuilder.setFlavourType(new Flavour("Пшеничная"));
    }

    public void createBirthDayCake(CakeBuilder cakeBuilder, int how_old){
        cakeBuilder.setNumberOfLayers(3);
        cakeBuilder.setCreamType(new Cream("Шоколадный"));
        cakeBuilder.setFlavourType(new Flavour("Пшеничная"));
        cakeBuilder.setGlazeType(new Glaze("Шоколадная"));
        cakeBuilder.setCandle(new Candle(how_old,"Восковая"));
    }
}
