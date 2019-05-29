package com.springboot.test.SpringBootTest.designModel.strategy;

public enum StarategyEnum {

    Add("+"){
        @Override
        public int exec(int a, int b) {
            return a + b;
        }
    },
    Sub("-"){
        @Override
        public int exec(int a, int b) {
            return a - b;
        }
    };

    StarategyEnum(String value) {
        this.value = value;
    }

    public String value = "";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public abstract int exec(int a,int b);
}
