import java.util.ArrayList;
import java.util.List;

public class Medium extends AI {

    private List<String> previousGuesses = new ArrayList<>();

    public Medium() {
        super.name = "MediumAI";
        super.secretCode = super.generateCode();
    }

    @Override
    protected String generateCode() {
        String guess = super.generateCode();
        while (previousGuesses.contains(guess)) {
            guess = super.generateCode();
        }
        previousGuesses.add(guess);
        return guess;
    }
}
