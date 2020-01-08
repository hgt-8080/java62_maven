package cn.bx.bid.ctrl;

import cn.bx.bid.entity.Page;
import cn.bx.bid.entity.Project;
import cn.bx.bid.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    //掉渣
//    public String search(HttpServletResponse resp, @RequestParam(name = "page",defaultValue = "1") int pageNo, @RequestParam(name = "limit",defaultValue = "10")int limit){
//        Page<Project> paging = projectService.paging(pageNo, limit, null, null);
//        String jsonStr="";
//        resp.getWriter().print(jsonStr);
//        return null;
//    }
    @GetMapping(value = "projectSearch",produces = "application/json;charset=utf-8")
    @ResponseBody
    public  Page<Project> search(HttpServletResponse resp, @RequestParam(name = "page",defaultValue = "1") int pageNo, @RequestParam(name = "limit",defaultValue = "10")int limit){
        Page<Project> paging = projectService.paging(pageNo, limit, null, null);

        return paging;
    }
    @GetMapping("toAddProject")
    public String toAdd(){
        return "projectAdd";
    }
    //上传的文件属性和类中的属性一定要不一致
    @PostMapping("addProject")
    public String add(HttpSession session, @ModelAttribute("p") Project p, @RequestParam(value="fujian",required = false) MultipartFile file){
        if(file.isEmpty()==false){
            //上传的文件名
            String oldName=file.getOriginalFilename();
            String prefix=oldName.substring(oldName.lastIndexOf('.'));
            String newFileName= UUID.randomUUID()+prefix;
            p.setProjectFilePath(newFileName);
            //保存文件到目录
            File path=new File(session.getServletContext().getRealPath("doc"));
            if(path.exists()==false)
                path.mkdirs();
            try {
                System.out.println(path+","+newFileName);
                file.transferTo(new File(path,newFileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println("项目："+p+"保存成功了");
        return "redirect:userSearch.jhtml";
    }


    public String add(HttpSession session, @ModelAttribute("p") Project p, MultipartFile []file) {
        for(MultipartFile f:file){
            if(f.getOriginalFilename().equals("zhengmian")){

            }else if(f.getOriginalFilename().equals("fanmian")){

            }
        }
        return null;
    }
        @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        // true:允许输入空值，false:不能为空值 }
    }
}
