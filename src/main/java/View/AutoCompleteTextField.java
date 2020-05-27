package View;

import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import org.controlsfx.control.textfield.AutoCompletionBinding;

import java.util.Arrays;
import java.util.Collection;

public class AutoCompleteTextField extends TextField {
    int pre = 0;
    //TODO 서버 연결하고 나서 데이터 받고 연결하기
    public AutoCompleteTextField() {
        new AutoCompletionTextFieldBinding(this, new Callback<AutoCompletionBinding.ISuggestionRequest, Collection>() {
            @Override
            public Collection call(AutoCompletionBinding.ISuggestionRequest param) {

                int now = getText().hashCode();
                if(getText().length() > 1 && pre < now) {
                    pre = getText().hashCode();
                    return Arrays.asList("Option 1", "Option 2");
                }
                else
                    pre = getText().hashCode();return null;
            }
        });
    }
}
