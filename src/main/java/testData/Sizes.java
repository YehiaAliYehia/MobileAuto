package testData;

public enum Sizes {
    Size1("");

    private String value;
    Sizes(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return value;
    }

}
