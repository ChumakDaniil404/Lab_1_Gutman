package ua.com.prologistic;

import java.util.Dictionary;

public interface IDictionaryInterface {
    public void check(String key, String value);
    public void readFileLineByLine(String way, Boolean invert);
    //public void readFileLineByLine(String way, Boolean invert);
    public void Chtenie();
    public void PerevernytSlovar();
    public void Poisk(String key);
    public void Ydalenie(String key);
    public void writeUsingOutputStream(String way);
    public String SlovarToString();
}
