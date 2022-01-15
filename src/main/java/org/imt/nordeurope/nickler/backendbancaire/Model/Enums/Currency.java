package org.imt.nordeurope.nickler.backendbancaire.Model.Enums;

public enum Currency {

    EURO(1f),
    US_DOLLAR(1.14f),
    POUND_STERLING(0.83f),
    YEN(130.39f),
    CANADIAN_DOLLAR(1.43f);

    private final float coefficient;

    Currency(float coefficient) {
        this.coefficient = coefficient;
    }

    public float getCoefficient() {
        return coefficient;
    }
}
