package testData;

public enum ProductsNames {
    Product1("مسك");

    private String value;
    ProductsNames(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return value;
    }


}
