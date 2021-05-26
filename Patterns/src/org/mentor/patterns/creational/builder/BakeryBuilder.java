package org.mentor.patterns.creational.builder;

import org.mentor.patterns.creational.builder.entities.Candle;
import org.mentor.patterns.creational.builder.entities.Cream;
import org.mentor.patterns.creational.builder.entities.Flavour;
import org.mentor.patterns.creational.builder.entities.Glaze;

public interface BakeryBuilder {
    void setFlavourType(Flavour flavour);
    void setGlazeType(Glaze glaze);
    void setCandle(Candle candle);
    void setCreamType(Cream cream);
}
