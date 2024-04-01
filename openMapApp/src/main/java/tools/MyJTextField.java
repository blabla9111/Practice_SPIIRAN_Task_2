package tools;

import javax.swing.*;
import java.util.Arrays;

public class MyJTextField extends JTextField {

    String dataType;
    public MyJTextField(String dataType){
        super();
        this.dataType = dataType;
    }

    public boolean ckeckInput(){
        String input = super.getText();
        switch (dataType){
            case "int":
                try {
                    Integer.parseInt(input);
                }
                catch (NumberFormatException e){
                    System.out.println(Arrays.toString(e.getStackTrace()));
                    return false;
                }
                break;
            case "double":
                try {
                    Double.parseDouble(input);
                }
                catch (NumberFormatException e){
                    System.out.println(Arrays.toString(e.getStackTrace()));
                    return false;
                }
                break;
            case "String":
                break;
            default: System.out.println("Неизвестный тип данных "+this.dataType+" заявлен для поля ");
        }
        return true;
    }
}
