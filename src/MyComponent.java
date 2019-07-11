import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.ui.Messages;

public class MyComponent implements ApplicationComponent {
    public void greet(){
        Messages.showInfoMessage("hello","info");
    }
}
