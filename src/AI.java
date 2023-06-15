import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AI extends Player {
    protected String generateCode() {
        List<Integer> codeList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            codeList.add(i);
        }
        Collections.shuffle(codeList);
        String code = "";
        for (int i = 0; i < 4; i++) {
            code += codeList.get(i);
        }
        return code;
    }
}

