import java.util.ArrayList;
import java.util.List;

public class Hard extends AI {
    private List<String> possibleGuesses = new ArrayList<>();

    public Hard() {
        super.name = "HardAI";
        super.secretCode = super.generateCode();
        generatePossibleGuesses("");
    }

    @Override
    protected String generateCode() {
        int index = (int) (Math.random() * possibleGuesses.size());
        return possibleGuesses.get(index);
    }

    public void modifyPossibleCode(int[] aiScore, String aiGuess) {
        GameController g = new GameController();
        List<String> tempPossibleGuesses = new ArrayList<>();
        for (String element : possibleGuesses) {
            int[] tempScore = new int[2];
            g.calculateScore(element, aiGuess, tempScore);
            if (tempScore[0] == aiScore[0] && tempScore[1] == aiScore[1]) {
                tempPossibleGuesses.add(element);
            }
        }
        possibleGuesses = tempPossibleGuesses;
    }

    public void generatePossibleGuesses(String guess) {
        if (guess.length() == 4) {
            possibleGuesses.add(guess);
            return;// using return to finish using this method
        }

        for (int i = 0; i <= 9; i++) {
            if (!guess.contains(String.valueOf(i))) {
                generatePossibleGuesses(guess + i);
            }
        }
    }
}
