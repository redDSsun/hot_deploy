package deploy;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.ui.Messages;

import java.io.IOException;

public class DeployAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Application application = ApplicationManager.getApplication();
        DeployManagement deployManagement = application.getComponent(DeployManagement.class);
        String filePath = (String) Messages.showInputDialog("info","file path: ", Messages.getQuestionIcon());
        try {
            deployManagement.sendFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
