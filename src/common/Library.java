package common;

import java.util.Scanner;

public class Library {
    Scanner scanner = new Scanner(System.in);
    public String getInputString(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }
}
