package acceler.ocdl.service.impl;

import acceler.ocdl.exception.OcdlException;
import acceler.ocdl.service.ProjectService;
import acceler.ocdl.service.TemplateService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultTemplateService implements TemplateService {

    @Autowired
    private ProjectService projectService;

    @Override
    public List<String> getTemplatesList(String type) {

        List<String> templatesList = getFile(Paths.get(projectService.getProjectConfiguration().getTemplatePath(), type).toString());
        return templatesList;
    }


    /*
     * 函数名：getFile
     * 作用：使用递归，输出指定文件夹内的所有文件
     * 参数：path：文件夹路径
     */
    private static List<String> getFile(String path) {
        // 获得指定文件对象
        List<String> nameList = new ArrayList<String>();

        try {
            File file = new File(path);
            // 获得该文件夹内的所有文件
            File[] array = file.listFiles();
            for (int i = 0; i < array.length; i++) {
                if (array[i].isFile())//如果是文件
                {

                    nameList.add(array[i].getName());
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new OcdlException("Get file error");
        }
        return nameList;
    }

    @Override
    public List<String> getTemplates2(String name, String type) {
        List<String> result = new ArrayList<>();
        StringBuilder code = new StringBuilder();
        File filename = null;
        InputStreamReader reader = null;
        BufferedReader br = null;
        try {
            /* 读入TXT文件 */
            String pathname = Paths.get(projectService.getProjectConfiguration().getTemplatePath(), type,name).toString(); // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            filename = new File(pathname); // 要读取以上路径的input。txt文件
            reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader
            br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = "";
            while (line != null && !line.equals("null")) {
                code.append("\n");
                line = br.readLine(); // 一次读入一行数据
                code.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new OcdlException("Read file error");
        } finally {
            try {
                br.close();
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        result.add(code.toString());
        result.add("I'm the description");
        return result;
    }


}
