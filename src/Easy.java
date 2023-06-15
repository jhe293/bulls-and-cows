import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Easy extends AI {
    public Easy() {
        super.name = "EasyAI";
        super.secretCode = generateCode();
    }

    // first forloop get the 1-9 into the codeList, second forloop get A-F into the codeList
    @Override
    protected String generateCode() {
        List<String> codeList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            codeList.add(String.valueOf(i));
        }
        for (int i = 65; i <= 70; i++) {
            codeList.add(String.valueOf((char) i));
        }
        //using Collections.shuffle() get random list from codeList and adding to String code as code/guess
        Collections.shuffle(codeList);
        String code = "";
        for (int i = 0; i < 4; i++) {
            code += codeList.get(i);
        }
        return code;
    }

}
