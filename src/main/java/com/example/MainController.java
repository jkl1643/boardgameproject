package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
public class MainController {
    private static final ApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
    public static int login = 0;
    public static int delaccount = 0;
    public static int delmemo = 0;
    public static int editaccount = 0;
    public static int result_search = 0;
    public static String userid2 = null;
    public static int state = 1;

    @Autowired
    private MemberDao memberDao;

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @RequestMapping("/login")
    public ModelAndView login(ModelAndView mav, Model model, String delid, String delpwd,
                              @RequestParam(value = "EMAIL", required = false, defaultValue = "0") String id,
                              @RequestParam(value = "PWD", required = false) String pwd,
                              @RequestParam(value = "PWD2", required = false) String pwd2,
                              @RequestParam(value = "NAME", required = false) String name,
                              @RequestParam(value = "TELEPHONE", required = false) String telephone,
                              @RequestParam(value = "ADDRESS", required = false) String address) {

        System.out.println("이메일 = " + id);

        mav.addObject("unknown_email", false);
        mav.addObject("email_pwd_match", false);
        mav.addObject("email_pwd_match2", false);
        mav.addObject("logout", false);
        mav.addObject("delaccount", false);
        mav.addObject("wrongemail", false);
        mav.addObject("select_date", false);
        mav.addObject("insert_memo", false);
        mav.addObject("created_account", false);
        mav.addObject("error", false);

        //ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
        System.out.println("login = " + login);
        System.out.println("delaccount = " + delaccount);
        System.out.println("delaccount = " + delaccount);



        RegisterRequest req = new RegisterRequest();
        if (login != 1 && delaccount != 1) {
            req.setEmail(id);
            req.setName(name);
            req.setPassword(pwd);
            req.setConfirmPassword(pwd2);
            req.setTel(telephone);
            req.setAddress(address);
        }
        if (!id.equals("0")) { //회원가입 아아디에 값을 입력했을때
            System.out.println("pwd = " + pwd);
            System.out.println("pwd2 = " + pwd2);
            System.out.println("telephone = " + telephone);
            System.out.println("address = " + address);
            if (!req.isPasswordEqualToConfirmPassword()) {
                mav.setViewName("login");
                mav.addObject("error", true);
                System.out.println("암호와 확인이 일치하지 않습니다.\n");
                return mav;
            }
            //회원가입 정보들을 입력하지 않앗을때
            if (pwd.isEmpty() || pwd2.isEmpty() || telephone.isEmpty() || address.isEmpty()) {
                mav.addObject("error", true);
                mav.setViewName("login");
                return mav;
            }
            try {
                MemberRegisterService memberRegSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class);
                memberRegSvc.regist(req); //회원가입
                mav.addObject("created_account", true);
            } catch (DuplicateMemberException e) {
                mav.addObject("error", true);
                System.out.println("이미 존재하는 이메일입니다.\n");
            } catch (Exception e) {
                mav.addObject("error", true);
                System.out.println("dd");
            }
            MemberLogin.loginEmail = id;
            mav.setViewName("login");
            id = "0";
            req.setEmail("0");
            System.out.println("계정생성 = " + id);

            return mav;
        } else
            mav.setViewName("login");
        id = "0";
        System.out.println("나중id22 = " + id);
        if (login == 1 && delaccount == 0) {//로그아웃
            login = 0;
            System.out.println("로그아웃 = " + login);
            MemberLogout lgo = ctx.getBean("lgo", MemberLogout.class);
            mav.addObject("logout", true);
            lgo.logout();
        }
        return mav;
    }

    @RequestMapping("/newaccount")
    public ModelAndView newaccount(ModelAndView mav) {
        mav.setViewName("newaccount");
        return mav;
    }

    @RequestMapping("/main")
    public ModelAndView main(Model model, String id, String oldpwd, String pwd, String pwd2, String name, String tel, String add,
                             @RequestParam(value = "MEMO", required = false) String memo,
                             @RequestParam(value = "IMAGE", required = false) String image,
                             @RequestParam(value = "DATE", required = false) String date,
                             @RequestParam(value = "DATE2", required = false) String date2) {
        System.out.println("-------------메인 ----------------");
        ModelAndView mav = new ModelAndView();
        mav.addObject("unknown_email", false);
        mav.addObject("email_pwd_match", false);
        mav.addObject("email_pwd_match2", false);
        mav.addObject("logout", false);
        mav.addObject("delaccount", false);
        mav.addObject("wrongemail", false);
        mav.addObject("select_date", false);
        mav.addObject("insert_memo", false);
        mav.addObject("created_account", false);
        mav.addObject("delmemo", false);
        mav.addObject("editaccount", false);
        mav.addObject("currentpwd", false);
        mav.addObject("chkpwd", false);
        mav.addObject("created_memo", false);
        mav.addObject("error", false);
        delaccount = 0;
        model.addAttribute("userid", userid2);
        System.out.println("id = " + id);
        System.out.println("login = " + login);
        System.out.println("delaccount = " + delaccount);
        System.out.println("delmemo = " + delmemo);
        System.out.println("editaccount = " + editaccount);
        System.out.println("memo = " + memo);
        if (id == null) {
            id = "0";
        }
        if (login == 0) { //이전에 로그인 한적이 없을때
            System.out.println("MemberLogin.loginEmail = " + MemberLogin.loginEmail);
            try {
                MemberLogin lgn = ctx.getBean("lgn", MemberLogin.class);
                lgn.login(id, pwd); //로그인
                System.out.println("id = " + id + ", pwd = " + pwd);
                userid2 = MemberLogin.loginEmail;
                model.addAttribute("userid", userid2);
                login = 1; //로그인을했을때
                mav.setViewName("main");
            } catch (MemberNotFoundException e) {
                System.out.println("존재하지 않는 이메일입니다.2\n");
                mav.addObject("unknown_email", true);
                id = "0";
                mav.setViewName("login");
            } catch (WrongIdPasswordException e) {
                System.out.println("이메일과 암호가 일치하지 않습니다.\n");
                mav.addObject("email_pwd_match", true);
                id = "0";
                mav.setViewName("login");
            } catch (IOException e) {
                id = "0";
                mav.setViewName("login");
            } catch (NullPointerException e) {
                id = "0";
                mav.setViewName("login");
            }
            //계정삭제, 메모삭제, 정보수정을 누르지 않았을때와 메모에 아무런값을입력하지 않았을때
        } else if (editaccount == 1) {
            editaccount = 0;
            ChangeInfoService changeInfoSvc = ctx.getBean("changeInfoSvc", ChangeInfoService.class);
            try {
                editaccount = 0;
                changeInfoSvc.changePassword(userid2, oldpwd, pwd, pwd2, name, tel, add);

                System.out.println("정보를 수정했습니다.\n");
                mav.addObject("editaccount", true);
            } catch (MemberNotFoundException e) {
                System.out.println("존재하지 않는 이메일입니다.\n");
                editaccount = 0;
            } catch (WrongIdPasswordException e) {
                System.out.println("이메일과 암호가 일치하지 않습니다.\n");
                editaccount = 0;
            } catch (PasswordNotMatchException e) {
                System.out.println("확인 비밀번호가 일치하지 않습니다.");
                mav.addObject("chkpwd", false);
                editaccount = 0;
            } catch (PasswordNotMatchException2 e) {
                System.out.println("현재 비밀번호가 일치하지 않습니다.");
                mav.addObject("currentpwd", true);
                editaccount = 0;
            }
        } else {
            mav.setViewName("main");
        }
        return mav;
    }

    @RequestMapping("/delaccount")
    public ModelAndView delaccount(Model model, String id, String pwd) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("email_pwd_match2", false);
        delaccount = 1;
        mav.addObject("wrongemail", false);
        mav.setViewName("delaccount");
        return mav;
    }

    @RequestMapping("/checkdelaccount")
    public ModelAndView checkdelaccount(Model model, String delid, String delpwd) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("email_pwd_match2", false);
        mav.addObject("wrongemail", false);
        if (delaccount == 1) {
            checkIdPassword checkidpwd = ctx.getBean("checkidpwd", checkIdPassword.class);
            System.out.println("아디 = " + delid + "비번22 = " + delpwd);
            try {
                checkidpwd.checkidpassword(delid, delpwd);
                MemberDao memberDao = ctx.getBean("memberDao", MemberDao.class);
                memberDao.delete(delid, delpwd);
                mav.addObject("delaccount", true);
                System.out.println("bbbbb");
                login = 0;
                delaccount = 0;
                mav.setViewName("login");
            } catch (MemberNotFoundException e) {
                mav.setViewName("delaccount");
                mav.addObject("wrongemail", true);
                System.out.println("잘못된 아이디 입력입니다.2");
                return mav;
            } catch (WrongIdPasswordException e) {
                mav.setViewName("delaccount");
                mav.addObject("email_pwd_match2", true);
                System.out.println("아이디와 비밀번호가 일치하지 않습니다.");
                return mav;
            } catch (NotMatchException e) {
                mav.setViewName("delaccount");
                mav.addObject("wrongemail", true);
                System.out.println("잘못된 아이디 입력입니다.");
                return mav;
            }
        }
        mav.setViewName("checkdelaccount");
        delaccount = 0;
        return mav;
    }

    @RequestMapping("/editaccount")
    public ModelAndView editaccount(Model model) {
        ModelAndView mav = new ModelAndView();
        editaccount = 1;
        System.out.println("userid2 = " + userid2);
        model.addAttribute("userid", userid2);
        mav.setViewName("editaccount");
        return mav;
    }

    @RequestMapping("/findpwd")
    public ModelAndView findpwd(Model model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("findpwd");
        return mav;
    }

    @RequestMapping("/resultfindpwd")
    public ModelAndView findpwd(Model model, String id, String tel) {
        ModelAndView mav = new ModelAndView();
        List<Member> results2 = memberDao.findpwd(id, tel);
        model.addAttribute("result", results2);
        mav.setViewName("resultfindpwd");
        return mav;
    }

}
