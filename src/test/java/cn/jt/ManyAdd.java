package cn.jt;

import cn.jt.controller.HelperController;
import cn.jt.dto.HelperSaveDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ManyAdd {
    private HelperController helperController = new HelperController();

    @Test
    void manyAdd() {

        String fileName = "D:\\file.txt"; // TXT文件的路径和名称
        List<HelperSaveDto> helperSaveDtoList = new ArrayList<>();
        List<String> lines = new ArrayList<>(); // 创建一个List用于保存每一行文本
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) { // 每次读取一行文本
                lines.add(line); // 将该行文本保存到List中
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 输出List中的元素
        for (String line : lines) {
            HelperSaveDto helperSaveDto = new HelperSaveDto();
            helperSaveDto.setNotebook(line);
            helperController.save(helperSaveDto);
        }

    }
}
