package deploy.utils;

import com.intellij.openapi.ui.Messages;
import deploy.pojo.DeployFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static deploy.contants.Contants.*;

public class CommandProcesseer {

    public ArrayList<DeployFile> getModifiedFiles(String currentProjectpath) {
        if (!currentProjectpath.endsWith("/")){
            currentProjectpath += "/";
        }
        ArrayList<DeployFile> deployFiles = new ArrayList<>();

        File dir = new File(currentProjectpath);
        String command = COMMAND;

        StringBuilder output = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec("cmd /c "+command, null, dir);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Changes not staged for commit:")){
                    break;
                }
                if (line.contains(DELETE_STATUS) || line.contains(MODIFY_STATUS) || line.contains(NEWFILE_STATUS)){
                    String status = getStatus(line);
                    String relativePath = getRelativePath(line);
                    File file = new File(currentProjectpath + relativePath);
                    DeployFile deployFile = new DeployFile();
                    deployFile.setFile(file);
                    deployFile.setOperation(STATUS_OPERATION_MAP.get(status));
                    deployFile.setRelativepath(relativePath);
                    deployFiles.add(deployFile);
                }
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                for (DeployFile file : deployFiles) {
                    output.append(file.getRelativepath() + "\n");
                }
                Messages.showInfoMessage(output.toString(),"files need deploy");
            } else {
                Messages.showInfoMessage(output.toString(),"error");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return deployFiles;
    }

    private String getStatus(String line) {
        return line.split(":")[0].trim();
    }

    private String getRelativePath(String line){
        return line.split(":")[1].trim();
    }


}
