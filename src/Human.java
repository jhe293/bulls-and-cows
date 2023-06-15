import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Human extends Player {
    private int level;
    private boolean isManual;
    private List<String> fileGuess;

    public Human() {
        super.name = inputName();
        System.out.println("Please enter your secret code");
        super.secretCode = inputValidCode();
        this.level = inputLevel();
        this.isManual = isManuallyInput();
        if (!isManual) {//when isManual is false, then continue readFile()
            fileGuess = readFile();
        }
    }

    public int getLevel() {
        return level;
    }

    public List<String> getFileGuess() {
        return fileGuess;
    }

    // guide human input their name
    private String inputName() {
        System.out.println("Please enter your name: ");
        return Keyboard.readInput();
    }

    //using either fileGuess or manual input to return the string as human's guess
    public String manualOrFile(int i) {
        if (!isManual && i <= fileGuess.size()) {
            return fileGuess.get(i - 1);
        } else {
            return inputValidCode();
        }
    }

    private boolean isDuplicatedCode(String code) {
        Set<Character> set = new HashSet<>();
        for (char c : code.toCharArray()) {
            if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'F')) {
                if (!set.add(c)) {
                    System.out.println("Your input has duplicated characters, please enter again: ");
                    return true;
                }
            } else {
                System.out.println("The code should only includes digit from 0-9 or characters from A-F");
                return true;
            }
        }
        return false;
    }

    // set the method for getting valid code/guess from human
    private String inputValidCode() {
        while (true) {
            String code = Keyboard.readInput();
            if (code.length() != 4) {
                System.out.println("The code length should be 4");
                continue;
            }
            if (!isDuplicatedCode(code)) {
                return code;
            }
        }
    }


    // guide human input their level
    private int inputLevel() {
        System.out.println("Hi " + getName() + "! Please select the AI you want to against with: ");
        System.out.println("\"1 - Easy\", \"2 - Medium\", and \"3 - Hard\"");
        System.out.println("Please type the game level you want: (enter the level or the digit)");
        while (true) {
            String userInput = Keyboard.readInput();//player can input anything, but we only need the correct integer or level name
            try {
                if (userInput.equalsIgnoreCase("Easy") || Integer.parseInt(userInput) == 1) {//can ignore either lowercase or capital case input, or cast string to integer
                    return level = 1;
                } else if (userInput.equalsIgnoreCase("Medium") || Integer.parseInt(userInput) == 2) {
                    return level = 2;
                } else if (userInput.equalsIgnoreCase("Hard") || Integer.parseInt(userInput) == 3) {
                    System.out.println("You chose 3 Hard level");
                    return level = 3;
                }
                System.out.println("Please enter an integer 1-3");
            } catch (NumberFormatException e) {//it will promote the reminder when the input is not a number
                System.out.println("Your input format is wrong. Please enter an integer 1-3!");
            }
        }
    }

    //guide human to choose which way for their guesses, true - manual input, false - read input.txt
    private boolean isManuallyInput() {
        System.out.println("Please select which way you want to guess the AI code (enter the digit): ");
        System.out.println("\"1 - enter your guess manually\" or \"2 - using the input.txt for your guesses\"");
        return inputChoices();
    }

    //make the lines in input.txt to be List<String>
    private List<String> readFile() {
        List<String> fileList = new ArrayList<>();
        System.out.println("Please enter the file name: ");
        while (true) {
            String fileName = Keyboard.readInput();
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    fileList.add(line);
                }
                return fileList;
            } catch (IOException e) {
                System.out.println("Can't find the file. Please enter the correct file name: ");
            }
        }
    }

    // for GameController to use when asking human to save the game result
    public boolean isGameResultSaved() {
        System.out.println("Please select if you wish to save the game result to a text file (enter the digit)");
        System.out.println("\"1 - Yes, save it\" or \"2 - No, I don't need it\"");
        return inputChoices();
    }

    // get boolean to indicate human choices
    private boolean inputChoices() {
        int choice = 0;
        while (true) {
            try {
                choice = Integer.parseInt(Keyboard.readInput());

                if(choice == 1){
                    return true;
                } else if (choice == 2) {
                    return false;
                }
                System.out.println("Please enter the integer 1 or 2: ");

            } catch (NumberFormatException e) {
                System.out.println("Your input format is wrong. Please enter the integer 1 or 2: ");
            }
        }
    }

}
