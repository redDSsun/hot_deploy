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
        DeployFile deployFile = new DeployFile();
        ArrayList<DeployFile> deployFiles = new ArrayList<>();

        File dir = new File(currentProjectpath);
        String command = "git status";

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
                System.out.println("====================");
                System.out.println(line);
                if (line.contains(DELETE_STATUS) || line.contains(MODIFY_STATUS) || line.contains(NEWFILE_STATUS)){
                    String status = getStatus(line);
                    String relativePath = getRelativePath(line);
                    File file = new File(currentProjectpath + relativePath);
                    deployFile.setFile(file);
                    deployFile.setOperation(OPERATION_STATUS_MAP.get(status));
                    deployFile.setRelativepath(relativePath);
                    deployFiles.add(deployFile);
                }
//                if (line.contains(MODIFY_STATUS)){
//                    String relativePath = getRelativePath(line);
//                    File modifiedFile = new File(currentProjectpath + relativePath);
//                    deployFile.setFile(modifiedFile);
//                    deployFile.setOperation(MODIFY_OPERATION);
//                    deployFile.setRelativepath(relativePath);
//                    deployFiles.add(deployFile);
//                }
//                if (line.contains(NEWFILE_STATUS)){
//                    String relativePath = getRelativePath(line);
//                    File newFile = new File(currentProjectpath + relativePath);
//                    deployFile.setFile(newFile);
//                    deployFile.setOperation(NEWFILE_OPERATION);
//                    deployFile.setRelativepath(relativePath);
//                    deployFiles.add(deployFile);
//                }
                output.append(line + "\n");
//                System.out.println(output.toString());
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                Messages.showInfoMessage(output.toString(),"modified files");
                for (DeployFile file:deployFiles
                     ) {
                    System.out.println(file.getFile().length());
                    System.out.println(file.getOperation());
                    System.out.println(file.getRelativepath());
                }
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
