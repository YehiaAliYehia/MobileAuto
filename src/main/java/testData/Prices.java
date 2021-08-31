package testData;

public enum Prices {
    Price1("tt");
    private String value;
    Prices(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return value;
    }
}
