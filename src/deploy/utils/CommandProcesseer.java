package deploy.utils;

import com.intellij.openapi.ui.Messages;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CommandProcesseer {

    public Map getModifiedFiles(String currentProjectpath) {
        Map<String, ArrayList> needDeployFiles = new HashMap();
        File dir = new File(currentProjectpath);
        String command = "git status";
        ArrayList<String> newFiles = new ArrayList<>();
        ArrayList<String> modifiedFiles = new ArrayList<>();
        ArrayList<String> deletedFiles = new ArrayList<>();

        StringBuilder output = new StringBuilder();
        try {
            Process process = Runtime.getRuntime().exec("cmd /c "+command, null, dir);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("====================");
                System.out.println(line);
                if (line.contains("deleted:")){
                    System.out.println(line.trim().split("deleted:")[0].trim());
                    deletedFiles.add(line.trim().split("deleted:")[0].trim());
                }
                if (line.contains("modified:")){
                    modifiedFiles.add(line.trim().split("modified:")[0].trim());
                }
                if (line.contains("new file:")){
                    newFiles.add(line.trim().split("new file:")[0].trim());
                }
                output.append(line + "\n");
//                System.out.println(output.toString());
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                needDeployFiles.put("newFiles", newFiles);
                needDeployFiles.put("deletedFiles", deletedFiles);
                needDeployFiles.put("modifiedFiles", modifiedFiles);
                Messages.showInfoMessage(output.toString(),"modified files");
//                System.out.println("Success!");
//                System.out.println(output);
//                System.exit(0);
            } else {
                Messages.showInfoMessage(output.toString(),"error");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return needDeployFiles;
    }

}
