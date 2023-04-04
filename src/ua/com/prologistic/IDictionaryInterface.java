package ua.com.prologistic;

public interface IDictionaryInterface {
    public void check(String key, String value);

    public void readFileLineByLine(String path, Boolean invert);

    public void outputFromTheDictionary();

    public void flipTheDictionary();

    public void search(String key);

    public void removal(String key);

    public void writeUsingOutputStream(String path);

    public String dictionaryToString();

    public void clear();

    public String getKeys();

    public String getValues();

    public void getKeyAndValues();
}
