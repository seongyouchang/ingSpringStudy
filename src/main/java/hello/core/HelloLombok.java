package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok hello = new HelloLombok();
        hello.setName("Hello");

        System.out.println("name = " + hello);
    }
}
