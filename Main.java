import java.io.*;
import java.util.Scanner;

class Calculator implements Serializable {
    private int x;

    public Calculator(int x) {
        this.x = x;
    }

    public double getY() {
        return x - Math.sin(x);
    }

    public static void saveToFile(Calculator y) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("upload.txt"))) {
            System.out.println("saved");
            oos.writeObject(y);
        } catch (Exception ex) {
            System.out.println("error");
        }
    }

    public static Calculator uploadY() {
        try (ObjectInputStream ios = new ObjectInputStream(new FileInputStream("upload.txt"))) {
            System.out.println("upload");
            return (Calculator) ios.readObject();
        } catch (Exception ex) {
            System.out.println("error");
            return null;
        }
    }
}


public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("input x: ");
        Calculator y = new Calculator(input.nextInt());
        Calculator.saveToFile(y);
        Calculator yUpload = y.uploadY();
        System.out.println(yUpload.getY());
    }
}
