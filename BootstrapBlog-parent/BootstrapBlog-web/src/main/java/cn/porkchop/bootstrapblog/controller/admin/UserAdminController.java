package cn.porkchop.bootstrapblog.controller.admin;

import cn.porkchop.bootstrapblog.pojo.TUser;
import cn.porkchop.bootstrapblog.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@RequestMapping("/admin/user")
@Controller
public class UserAdminController {
    @Autowired
    private UserService userService;

    /**
     * 更新
     *
     * @date 2018/3/8 16:41
     * @author porkchop
     */
    @ResponseBody
    @RequestMapping("/update")
    public HashMap<String, String> update(TUser user) {
        userService.update(user);
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "ok");
        return map;
    }

    /**
     * 查询
     *
     * @date 2018/3/8 16:42
     * @author porkchop
     */
    @RequestMapping("/showModifyPage")
    public String showModifyPage(long id, Model model) {
        TUser tUser = userService.findById(id);
        model.addAttribute("tUser", tUser);
        return "admin/modifyInfo";
    }

    /**
     * 修改密码
     *
     * @date 2018/3/8 19:23
     * @author porkchop
     */
    @RequestMapping("/changePassword")
    @ResponseBody
    public HashMap<String, String> changePassword(TUser user) {
        userService.changePassword(user);
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "ok");
        return map;
    }

    /**
     * 登出
     *
     * @date 2018/3/8 20:04
     * @author porkchop
     */
    @RequestMapping("/logout")
    public String logout() throws Exception {
        SecurityUtils.getSubject().logout();
        return "redirect:/login.html";
    }
}
