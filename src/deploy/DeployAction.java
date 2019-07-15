package deploy;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import deploy.pojo.DeployFile;
import deploy.utils.CommandProcesseer;

import java.io.IOException;
import java.util.ArrayList;

public class DeployAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);
        String path = project.getProjectFilePath();
        String currenProjectPath = path.split(".idea/misc.xml")[0];
        ArrayList<DeployFile> needDeployFiles = new CommandProcesseer().getModifiedFiles(currenProjectPath);
        Application application = ApplicationManager.getApplication();
        DeployManagement deployManagement = application.getComponent(DeployManagement.class);
        if(deployManagement.getConnect() == null){
            Messages.showErrorDialog("Please connect first", "error");
        }
//        String filePath = (String) Messages.showInputDialog("info","file path: ", Messages.getQuestionIcon());
        try {

            deployManagement.sendFile(needDeployFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
