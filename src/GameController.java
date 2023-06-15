import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    public Human human;
    public AI ai;
    public List<String> record = new ArrayList<>();//using this to add the game result information in the ArrayList
    public int turns = 7;// allow human or the coder to change the maximum turns
    public static void main(String[] args) {
        new GameController().start();
    }

    private void start() {
        System.out.println("Welcome to the Bulls & Cows Game!");
        human = new Human();
        System.out.println("----------");
        selectLevel();
        startGame();
        endGame();
    }

    private void selectLevel() {//the value from human's inputLevel will make create different ai object
        if (human.getLevel() == 1) {
            ai = new Easy();
            System.out.println("You chose 1 - Easy level");
        } else if (human.getLevel() == 2) {
            ai = new Medium();
            System.out.println("You chose 2 - Medium level");
        } else {
            ai = new Hard();
            System.out.println("You chose 3 - Hard level");
        }
    }

    private void startGame() {
        record.add("Your code is " + human.getSecretCode());
        record.add(ai.getName() + "'s code is " + ai.getSecretCode());
        for (int i = 1; i <= turns; i++) {
            System.out.println("----------");
            System.out.println("Round " + i + ": ");
            System.out.println("Please enter your guess: ");
            String humanGuess = human.manualOrFile(i);
            record.add("---");
            record.add("Round " + i + ": ");

            int[] humanScore = new int[2];
            calculateScore(humanGuess, ai.getSecretCode(), humanScore);
            System.out.println("Your guess is " + humanGuess);
            System.out.println("Your scores - Bulls: " + humanScore[0] + ", Cows: " + humanScore[1]);
            record.add("You guessed " + humanGuess + ", scoring - Bulls: " + humanScore[0] + ", Cows: " + humanScore[1]);
            if (humanScore[0] == 4) {
                System.out.println("Congratulations " + human.getName() + ", you win!");
                record.add("You win!");
                break;
            }

            System.out.println();

            String aiGuess = ai.generateCode();
            int[] aiScore = new int[2];
            calculateScore(aiGuess, human.getSecretCode(), aiScore);
            if (ai instanceof Hard) {
                ((Hard) ai).modifyPossibleCode(aiScore, aiGuess);
            }
            System.out.println(ai.getName() + "'s guess is " + aiGuess);
            System.out.println("AI's scores - Bulls: " + aiScore[0] + ", Cows: " + aiScore[1]);
            record.add("AI guessed " + aiGuess + ", scoring - Bulls: " + aiScore[0] + ", Cows: " + aiScore[1]);
            if (aiScore[0] == 4) {
                System.out.println(ai.getName() + " wins! Wish you good luck next time!");
                record.add("AI wins!");
                break;
            }

            System.out.println();
            // Check for end of game
            if (i == turns) {
                System.out.println("\n" + "Game over, it's a draw!");
                record.add("It's a draw!");
                break;
            } else {
                System.out.println("You have " + (turns - i) + " turns left.");
            }
        }
        System.out.println();
        printAISecretCode();
        System.out.println();
    }

    public int[] calculateScore(String guess, String targetCode, int[] score) {
        for (int i = 0; i < 4; i++) {
            char guessDigit = guess.charAt(i);
            if (targetCode.indexOf(guessDigit) != -1) {
                if (targetCode.charAt(i) == guess.charAt(i)) {
                    score[0]++;//bull
                } else {
                    score[1]++;//cow
                }
            }
        }
        return score;
    }

    private void printAISecretCode() {
        System.out.println(ai.getName() + "'s secret code is " + ai.getSecretCode());
    }

    //print the game records
    private void endGame() {
        if (human.isGameResultSaved()) {
            System.out.println("Please enter the file name you want to save the record in");
            String fileName = Keyboard.readInput();
            try (BufferedWriter bW = new BufferedWriter(new FileWriter(fileName))) {
                bW.write("Bull and Cow Game Result: " + "\n");
                for (String line : record) {
                    bW.write(line + "\n");
                }
            } catch (IOException e) {
                System.out.println("Please enter an valid file name: ");
            }
            System.out.println("The game records are saved in \"" + fileName +".txt\" now. \nGood bye!");
        } else {
            System.out.println("Game over, good bye!");
        }
    }
}