package deploy;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.ui.Messages;

import java.io.IOException;

public class DeployConnect extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        String ip = (String)Messages.showInputDialog("please input ip", "please input ip", Messages.getQuestionIcon());
        String port = Messages.showInputDialog("please input port", "please input port", Messages.getQuestionIcon());
        int portNumber = Integer.parseInt(port);
        Application application = ApplicationManager.getApplication();
        DeployManagement deployManagement = application.getComponent(DeployManagement.class);
        try {
            deployManagement.connectServer(ip, portNumber);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
