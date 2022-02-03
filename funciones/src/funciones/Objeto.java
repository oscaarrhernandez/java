package funciones;

import java.io.Serializable;
import java.util.ArrayList;

public class Objeto implements Serializable {
    String atr1, atr2, atr3;
    int atr4;
    float atr5;
    double atr6;
    ArrayList<String> atr7 = new ArrayList<>();

    public Objeto(String atr1, String atr2, String atr3, int atr4, float atr5,
                    double atr6, ArrayList<String> atr7) {
        this.atr1 = atr1;
        this.atr2 = atr2;
        this.atr3 = atr3;
        this.atr4 = atr4;
        this.atr5 = atr5;
        this.atr6 = atr6;
        this.atr7 = atr7;
    }

    public String getAtr1() {
        return atr1;
    }
    public void setAtr1(String atr1) {
        this.atr1 = atr1;
    }
    public String getAtr2() {
        return atr2;
    }
    public void setAtr2(String atr2) {
        this.atr2 = atr2;
    }
    public String getAtr3() {
        return atr3;
    }
    public void setAtr3(String atr3) {
        this.atr3 = atr3;
    }
    public int getAtr4() {
        return atr4;
    }
    public void setAtr4(int atr4) {
        this.atr4 = atr4;
    }
    public float getAtr5() {
        return atr5;
    }
    public void setAtr5(float atr5) {
        this.atr5 = atr5;
    }
    public double getAtr6() {
        return atr6;
    }
    public void setAtr6(double atr6) {
        this.atr6 = atr6;
    }
    public ArrayList<String> getAtr7() {
        return atr7;
    }
    public void setAtr7(ArrayList<String> atr7) {
        this.atr7 = atr7;
    }
    
    
}
