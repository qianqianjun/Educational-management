package buct.software.controller;

import buct.software.domain.Power;
import buct.software.domain.User;
import buct.software.service.PowerService;
import buct.software.service.SemesterService;
import buct.software.service.StudentService;
import buct.software.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class PowerControllerPage {
    @Autowired
    PowerService powerService;
    @Autowired
    SemesterService semesterService;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    //传参...
    private Integer tmpcno;
    private Integer tmpSemesterId;

    public boolean checkPower(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return false;
        }
        return user.getType() == 2;
    }

    @RequestMapping("/ExcelInsert")
    public String excelInsert(HttpServletRequest request) {
        return "StudentsFileAdd";
    }

    @ResponseBody
    @RequestMapping("/ExcelAfterInsert")
    public String excelAfterInsert(HttpServletRequest request, @RequestParam("file") MultipartFile multfile) {
        // 获取文件名
        String fileName = multfile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 用uuid作为文件名，防止生成的临时文件重复
        File file = null;
        try {
            file = File.createTempFile(getUUID(), prefix);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // MultipartFile to File
        try {
            multfile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* 测试数据*/
        tmpSemesterId = 1;
        tmpcno = 1;

        if (fileName == null && "".equals(fileName)) {
            return "文件名不能为空！";
        } else {
            if (fileName.endsWith("xls") || fileName.endsWith("xlsx")) {
//                Boolean isOk = (tmpSemesterId,tmpcno,file);
                boolean ok = false;
                try {
                    ok = studentService.excel(file);
                } catch (Exception e) {
                    return "导入失败";
                }
                //导入结束时，删除临时文件
                deleteFile(file);
                if (ok) {
                    return "导入成功！";
                } else {
                    return "导入失败！";
                }
            }
            return "文件格式错误！";
        }

    }

    @ResponseBody
    @RequestMapping("/ExcelAfterInsertForT")
    public String excelAfterInsertForT(HttpServletRequest request, @RequestParam("file") MultipartFile multfile) {
        // 获取文件名
        String fileName = multfile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 用uuid作为文件名，防止生成的临时文件重复
        File file = null;
        try {
            file = File.createTempFile(getUUID(), prefix);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // MultipartFile to File
        try {
            multfile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* 测试数据*/
        tmpSemesterId = 1;
        tmpcno = 1;

        if (fileName == null && "".equals(fileName)) {
            return "文件名不能为空！";
        } else {
            if (fileName.endsWith("xls") || fileName.endsWith("xlsx")) {
//                Boolean isOk = (tmpSemesterId,tmpcno,file);
                boolean ok = false;
                try {
                    ok = teacherService.excel(file);
                } catch (Exception e) {
                    return "导入失败";
                }
                //导入结束时，删除临时文件
                deleteFile(file);
                if (ok) {
                    return "导入成功！";
                } else {
                    return "导入失败！";
                }
            }
            return "文件格式错误！";
        }

    }

    /**
     * 获取32位UUID字符串 临时文件名
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 删除临时文件
     *
     * @param files
     */
    private void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    @RequestMapping("/GoHomePage")
    public String homePage(Map<String, Object> parMap, HttpServletRequest request) {
        Power status = powerService.getStatus();
        parMap.put("power", status);
        request.setAttribute("currentSemesterInfo", semesterService.getCurrentSemesterInfo());
        return "HomePage";
    }

    @RequestMapping("/PowerManage")
    public String powerControll(Map<String, Object> parMap, HttpServletRequest request) {
        if (checkPower(request) == false) {
            return "error";
        }
        request.setAttribute("score", powerService.getScore());
        request.setAttribute("abnormal", powerService.getAbnormal());
        request.setAttribute("selectCourse", powerService.getSelectCourse());
        return "PowerManage";
    }

    @PostMapping("/OpenSelectCourse")
    public String openSelectCourse(Map<String, Object> parMap, HttpServletRequest request) {
        if (checkPower(request) == false) {
            return "error";
        }
        powerService.openSelectCourse();
        return "redirect:/PowerManage";
    }

    @PostMapping("/OpenScore")
    public String openScore(Map<String, Object> parMap, HttpServletRequest request) {
        if (checkPower(request) == false) {
            return "error";
        }
        powerService.openScore();
        return "redirect:/PowerManage";
    }

    @PostMapping("/OpenAbnormal")
    public String openAbnormal(Map<String, Object> parMap, HttpServletRequest request) {
        if (checkPower(request) == false) {
            return "error";
        }
        powerService.openAbnormal();
        return "redirect:/PowerManage";
    }

    @PostMapping("/CloseAbnormal")
    public String closeAbnormal(Map<String, Object> parMap, HttpServletRequest request) {
        if (checkPower(request) == false) {
            return "error";
        }
        powerService.closeAbnormal();
        return "redirect:/PowerManage";
    }

    @PostMapping("/CloseScore")
    public String closeScore(Map<String, Object> parMap, HttpServletRequest request) {
        if (checkPower(request) == false) {
            return "error";
        }
        powerService.closeScore();
        return "redirect:/PowerManage";
    }

    @PostMapping("/CloseSelectCourse")
    public String closeSelectCourse(Map<String, Object> parMap, HttpServletRequest request) {
        if (checkPower(request) == false) {
            return "error";
        }
        powerService.closeSelectCourse();
        return "redirect:/PowerManage";
    }
//    @RequestMapping("/HomePage")
}
