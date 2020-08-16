package com.example;

//import com.sun.org.apache.xpath.internal.operations.Mod;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import custom_asking.Custom;
import custom_asking.CustomChange;
import custom_asking.CustomDao;
import custom_asking.CustomRequest;
import custom_asking.CustomWrite;


import MyGameRecord.MyGameRecord;
import MyGameRecord.MyGameRecordDao;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {
    private static final ApplicationContext ctx = new AnnotationConfigApplicationContext(JavaConfig.class);
    public static int login = 0;
    public static int delaccount = 0;
    public static int delmemo = 0;
    public static int editaccount = 0;
    public static String userid2 = null;
    public static String userNickname = null;
    public static int state = 1;

    @Autowired
    private Main_Server Server;

    @Autowired
    private MemberDao memberDao;

    //윤수명 추가 2
    @Autowired
    private CustomWrite customwrite;

    @Autowired
    private CustomDao customdao;

    @Autowired
    private CustomChange customchange;

    @Autowired
    private MyGameRecordDao mygamerecordDao;

    // 윤수명 끝
    public void setCustomChange(CustomChange customchange) {
        this.customchange = customchange;
    }

    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    public void setCustomDao(CustomDao customdao) {
        this.customdao = customdao;
    }

    public void setCustomWrite(CustomWrite customwrite) {
        this.customwrite = customwrite;
    }

    public void setMyGameRecordDao(MyGameRecordDao mygamerecordDao) {
        this.mygamerecordDao = mygamerecordDao;
    }


    @RequestMapping("/logout")
    public ModelAndView logout(ModelAndView mav, HttpSession session) {
        mav.addObject("unknown_email", false);
        mav.addObject("email_pwd_match", false);
        mav.addObject("email_pwd_match2", false);
        mav.addObject("logout", false);
        mav.addObject("delaccount", false);
        mav.addObject("wrongemail", false);
        mav.addObject("created_account", false);
        mav.addObject("error", false);
        //mav.addObject("loginduplicate", false);
        //mav.addObject("id", id);
        Member name = (Member) session.getAttribute("mem");
        //if (name ) {//로그아웃
        login = 0;
        if (login == 0) {
            mav.addObject("login", 0);
        } else {
            mav.addObject("login", 1);
        }
        session.invalidate();
        System.out.println("로그아웃 = " + login);

        MemberLogout lgo = ctx.getBean("lgo", MemberLogout.class);
        mav.addObject("loginduplicate", false);
        mav.addObject("logout", true);
        lgo.logout();

        //}
        mav.setViewName("home");
        return mav;
    }

    @RequestMapping("/home")
    public ModelAndView login(ModelAndView mav, HttpSession session,
                              @RequestParam(value = "EMAIL", required = false, defaultValue = "0") String id,
                              @RequestParam(value = "PWD", required = false) String pwd,
                              @RequestParam(value = "PWD2", required = false) String pwd2,
                              @RequestParam(value = "NICKNAME", required = false) String nickname) {
        System.out.println("--------홈------------");
        System.out.println("이메일 = " + id);
        try {
            /*Member member = memberDao.selectByEmail(id);

            session.setAttribute("mem", member);*/
            Member name = (Member) session.getAttribute("mem");
            System.out.println("home name = " + name);
            login = 0;
        } catch (Exception e) {
            login = 1;
        }

        mav.addObject("unknown_email", false);
        mav.addObject("email_pwd_match", false);
        mav.addObject("email_pwd_match2", false);
        mav.addObject("logout", false);
        mav.addObject("delaccount", false);
        mav.addObject("wrongemail", false);
        mav.addObject("created_account", false);
        mav.addObject("error", false);
        mav.addObject("id", id);
        mav.addObject("loginduplicate", false);
        if (login == 0) {
            mav.addObject("login", 0);
        } else {
            mav.addObject("login", 1);
        }
        System.out.println("login = " + login);
        System.out.println("delaccount = " + delaccount);


        RegisterRequest req = new RegisterRequest();
        if (login != 1 && delaccount != 1) {
            req.setEmail(id);
            req.setNickname(nickname);
            req.setPassword(pwd);
            req.setConfirmPassword(pwd2);
        }

        System.out.println("id = " + id);

        if (!id.equals("0")) { //회원가입 아아디에 값을 입력했을때
            System.out.println("pwd = " + pwd);
            System.out.println("pwd2 = " + pwd2);
            if (!req.isPasswordEqualToConfirmPassword()) {
                mav.setViewName("home");
                mav.addObject("error", true);
                System.out.println("암호와 확인이 일치하지 않습니다.\n");
                return mav;
            }
            //회원가입 정보들을 입력하지 않앗을때
            if (id.isEmpty() || pwd.isEmpty() || pwd2.isEmpty() || nickname.isEmpty()) {
                mav.addObject("error", true);
                mav.setViewName("home");
                return mav;
            }
            try {
                MemberRegisterService memberRegSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class);
                System.out.println("1");
                memberRegSvc.regist(req); //회원가입
                System.out.println("2");
                mav.addObject("created_account", true);
            } catch (DuplicateMemberException e) {
                mav.addObject("error", true);
                System.out.println("이미 존재하는 이메일입니다.\n");
            } catch (Exception e) {
                mav.addObject("error", true);
                System.out.println("dd");
            }
            MemberLogin.loginEmail = id;
            System.out.println("계정생성 = " + id);
            id = "0";
            req.setEmail("0");
            mav.setViewName("home");
            return mav;
        } else {
            mav.setViewName("home");
        }
        id = "0";
        System.out.println("나중id22 = " + id);
        /*if (login == 1 && delaccount == 0) {//로그아웃
            login = 0;
            if(login == 0) {
                mav.addObject("login", 0);
            } else {
                mav.addObject("login", 1);
            }
            session.invalidate();
            System.out.println("로그아웃 = " + login);

            MemberLogout lgo = ctx.getBean("lgo", MemberLogout.class);
            mav.addObject("loginduplicate", false);
            mav.addObject("logout", true);
            lgo.logout();

        }*/
        /*if(id == null){
            session.invalidate();
        }*/
        return mav;
    }

    @RequestMapping("/newaccount")
    public ModelAndView newaccount(ModelAndView mav) {
        mav.setViewName("newaccount");
        return mav;
    }

    @RequestMapping("/main")
    public ModelAndView main(Model model, String id, HttpServletResponse response, String saveId,
                             String oldpwd, String pwd, String pwd2, String nickname, HttpSession session) {
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
        mav.addObject("login", 0);
        mav.addObject("loginduplicate", false);
        System.out.println("login1 = " + login);

        Member member = memberDao.selectByEmail(id);
        // MyGameRecord record = mygamerecordDao.selectByNickname(nickname); // 수명
        System.out.println("member = " + member);

        session.setAttribute("idid", id);
        delaccount = 0;
        model.addAttribute("userid", id);
        System.out.println("id = " + id);
        System.out.println("delaccount = " + delaccount);
        System.out.println("delmemo = " + delmemo);
        System.out.println("editaccount = " + editaccount);
        if (login == 1) {
            mav.addObject("id2", id);
        }

        /*if (id == null) {
            id = "0";
        }*/
        String idid = (String) session.getAttribute("idid");
        if (login == 0) { //이전에 로그인 한적이 없을때
            Member name = (Member) session.getAttribute("mem");
            System.out.println("name = " + name);
            if (name != null) { //세션 있을떄 로그인 다른곳에서 돼 있을때
                System.out.println("ididid = " + name.getEmail());
                if (id.equals(name.getEmail())) {
                    System.out.println("중복");
                    mav.addObject("loginduplicate", true);
                } else {
                    session.setAttribute("mem", member);
                    //          session.setAttribute("rec", record); // 수명
                    System.out.println("셋됨");
                }
            } else {
                session.setAttribute("mem", member);
//                session.setAttribute("rec", record); // 수명
                System.out.println("널임");
            }

            System.out.println("MemberLogin.loginEmail = " + MemberLogin.loginEmail);
            try {
                MemberLogin lgn = ctx.getBean("lgn", MemberLogin.class);
                lgn.login(id, pwd); //로그인

                mav.addObject("login", 1);
                System.out.println("login = " + login);
                System.out.println("id = " + id + ", pwd = " + pwd);
                userid2 = MemberLogin.loginEmail;
                userNickname = nickname;
                model.addAttribute("userid", userid2);
                login = 1; //로그인을했을때
                if (saveId != null) {
                    Cookie cookie = new Cookie("saveId", id);
                    response.addCookie(cookie);
                }
                mav.setViewName("home");
            } catch (MemberNotFoundException e) {
                System.out.println("존재하지 않는 이메일입니다.2\n");
                /*if(id == null) {
                    mav.addObject("unknown_email", true);
                }*/
                mav.addObject("unknown_email", true);

                id = "0";
                mav.setViewName("home");
            } catch (WrongIdPasswordException e) {
                System.out.println("이메일과 암호가 일치하지 않습니다.\n");
                mav.addObject("email_pwd_match", true);
                id = "0";
                mav.setViewName("home");
            } catch (IOException e) {
                id = "0";
                mav.setViewName("home");
            } catch (NullPointerException e) {
                id = "0";
                mav.setViewName("home");
            }
            //계정삭제, 정보수정을 누르지 않았을때
        } else if (editaccount == 1) {
            editaccount = 0;
            ChangeInfoService changeInfoSvc = ctx.getBean("changeInfoSvc", ChangeInfoService.class);
            try {
                editaccount = 0;
                changeInfoSvc.changePassword(userid2, oldpwd, pwd, pwd2, nickname);
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
            mav.setViewName("home");
        }
        /*String name = (String)session.getAttribute("id");
        System.out.println("ididid = " + name);
        if(id.equals(name)){
            System.out.println("이미 로그인한 아이디");
        }*/
        System.out.println("login2 = " + login);
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
                mav.setViewName("home");
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
        System.out.println("userid22 = " + userid2);
        model.addAttribute("userid", userid2);
        model.addAttribute("userpassword", userNickname);
        mav.setViewName("editaccount");
        return mav;
    }

    @RequestMapping("/findaccount")
    public ModelAndView findaccount(Model model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("findaccount");
        return mav;
    }

    @RequestMapping("/findpwd")
    public ModelAndView findpwd(Model model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("findpwd");
        return mav;
    }

    @RequestMapping("/findid")
    public ModelAndView findid(Model model, String id, HttpServletRequest req, HttpServletResponse resp) {
        /*String host = "smtp.naver.com";
        String password = "";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 465);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");


        StringBuffer temp = new StringBuffer();
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            int rIndex = rnd.nextInt(3);
            switch (rIndex) {
                case 0:
                    // a-z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    // A-Z
                    temp.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    // 0-9
                    temp.append((rnd.nextInt(10)));
                    break;
            }
        }
        String AuthenticationKey = temp.toString();
        System.out.println(AuthenticationKey);

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(id, password);
            }
        });

        try {
            MimeMessage msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(id, "KH"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(id));

            // 메일 제목
            msg.setSubject("안녕하세요  인증 메일입니다.");
            // 메일 내용
            msg.setText("인증 번호는 :" + temp);

            Transport.send(msg);
            System.out.println("이메일 전송");

        } catch (Exception e) {
            e.printStackTrace();// TODO: handle exception
        }
        HttpSession saveKey = req.getSession();
        saveKey.setAttribute("AuthenticationKey", AuthenticationKey);*/


        ModelAndView mav = new ModelAndView();
        mav.setViewName("findid");
        return mav;
    }

    @RequestMapping("/resultfindpwd")
    public ModelAndView findpwd(Model model, String id, String nickname) {
        ModelAndView mav = new ModelAndView();
        List<Member> results2 = memberDao.findpwd(id, nickname);
        model.addAttribute("result", results2);
        mav.setViewName("resultfindpwd");
        return mav;
    }

    @RequestMapping("/gamescreen")
    public ModelAndView gamescreen(Model model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("gamescreen");
        return mav;
    }

    //윤수명 고객문의 컨트롤러1---------
    @RequestMapping("/custom")
    public String handleStep1() {

        return "custom";
    }
/*
    @RequestMapping("/record")
    public String handleStep7() {

    	return "mygamerecord";
    }
  */

    @RequestMapping("/customwrite")
    public String handleStep2(Model model) {
        model.addAttribute("customrequest", new CustomRequest());
        return "customwrite";
    }

    @PostMapping("/customwriteok")
    public String handleStep3(CustomRequest request) {
        customwrite.inputdata(request);
        return "customwriteok";

    }

    @GetMapping(value = "/customchange/{count}")
    public String change(@PathVariable("count") Long memCount, Model model) {
        Custom custom1 = customdao.selectByCount(memCount);

        model.addAttribute("custom1", custom1);
        return "customchange";
    }

    @RequestMapping("/customchange/customchangeok") // 수정함 병렬
    public String handleStep5(Model model, Long count1, String title1, String content1, String name1, String email1) {

        customchange.changedata(count1, title1, content1, name1, email1);
        return "customchangeok";
    }


    @GetMapping(value = "/custom")
    public String list(Model model) {
        List<Custom> questionlist = customdao.selectAll();
        model.addAttribute("QuestionList", questionlist);
        return "custom";
    }


    @GetMapping(value = "/record")
    public String myresult(Model model, String nickname) {
        MyGameRecord record = mygamerecordDao.selectByNickname(nickname);
        model.addAttribute("myrecord", record);
        return "mygamerecord";

    }

    @GetMapping(value = "/content/{count}")
    public String detail(@PathVariable("count") Long memCount, Model model) {
        Custom custom = customdao.selectByCount(memCount);
        model.addAttribute("custom", custom);
        return "customread";
    }

    @GetMapping(value = "/delete/{count}")
    public String delete(@PathVariable("count") Long memCount, Model model) {
        Custom custom = customdao.selectByCount(memCount);
        customdao.delete(custom);
        return "customdeleteok";
    }

    @RequestMapping("/gameranking")
    public String handleStep6() {
        return "gameranking";
    }
    //윤수명끝----------------------------

    //  /////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/lobby")
    public ModelAndView lobby_start(Model model) {
        ModelAndView mv = new ModelAndView();
        // model.addAttribute("Room_list", a.getRoom_list());
        // a.create("test");
        mv.setViewName("lobby");
        return mv;
    }



        @PostMapping("/join")
        public ModelAndView lobby_join (Model model, HttpSession
        session, @RequestParam(value = "joinid", required = false) String ID,
                @RequestParam(value = "joinpw", required = false) String PW){
            ModelAndView mv = new ModelAndView();
            if (Server.getRoom_list().get(ID).getPassword().equals(PW)) {

                Server.select(ID, PW, (String) session.getAttribute("idid"));
                System.out.println("방 접속 : " + ID + " / " + session.getAttribute("idid"));

                model.addAttribute("id", ID);
                model.addAttribute("pw", PW);
                mv.setViewName("Game_room");
            } else
                mv.setViewName("Game_lobby");
            return mv;
        }

        @GetMapping("/test")
        public ModelAndView test_lobby (Model model, HttpSession session){
            ModelAndView mv = new ModelAndView();
            model.addAttribute("userid", session.getAttribute("idid"));
            mv.setViewName("Game_lobby");
            return mv;
        }
//
        @PostMapping("/createroom")
        public ModelAndView CreateRoom (Model model,
                @RequestParam(value = "Createroomname", required = true) String name,
                @RequestParam(value = "Createroomgame", required = true) String game,
                @RequestParam(value = "Createroompw", defaultValue = "", required = false) String pw)
        {
            Server.create(name, game, pw);
            model.addAttribute("Room_list", Server.getRoom_list().values());
            ModelAndView mv = new ModelAndView();
            mv.setViewName("Game_lobby"); // room 만든후 .
            return mv;
        }

        @GetMapping("/refreshlist")
        public ModelAndView Refresh (Model model)
        {
            ModelAndView mv = new ModelAndView();
            model.addAttribute("Room_list", Server.getRoom_list().values());
            mv.setViewName("Game_roomlist"); // room 만든후 .
            return mv;
        }

        @GetMapping("/gamerank")
        public ModelAndView Gamerank (Model model)
        {
            ModelAndView mv = new ModelAndView();
            DBcontroller control = (DBcontroller) ctx.getBean("dbcontrol");
            model.addAttribute("Rank_list", control.GameRank_list());
            mv.setViewName("Game_rank");
            return mv;
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////