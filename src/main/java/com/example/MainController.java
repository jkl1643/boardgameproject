package com.example;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


import custom_asking.Custom;
import custom_asking.CustomChange;
import custom_asking.CustomDao;
import custom_asking.CustomRequest;
import custom_asking.CustomWrite;


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
    private Room_Server a = new Room_Server();
    @Autowired
    private MemberDao memberDao;

    //윤수명 추가 2
    @Autowired
    private CustomWrite customwrite;
    
    @Autowired
    private CustomDao customdao;
    
    @Autowired
    private CustomChange customchange;
    
    // 윤수명 끝
    public void setCustomChange(CustomChange customchange) {
		this.customchange = customchange;
	}
    
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
   
	public void setCustomDao(CustomDao customdao) {
		this.customdao =customdao;
	}

    public void setCustomWrite(CustomWrite customwrite) {
		this.customwrite = customwrite;
	}


    @RequestMapping("/home")
    public ModelAndView login(ModelAndView mav, HttpSession session,
                              @RequestParam(value = "EMAIL", required = false, defaultValue = "0") String id,
                              @RequestParam(value = "PWD", required = false) String pwd,
                              @RequestParam(value = "PWD2", required = false) String pwd2,
                              @RequestParam(value = "NICKNAME", required = false) String nickname) {
        System.out.println("--------홈------------");
        System.out.println("이메일 = " + id);

        mav.addObject("unknown_email", false);
        mav.addObject("email_pwd_match", false);
        mav.addObject("email_pwd_match2", false);
        mav.addObject("logout", false);
        mav.addObject("delaccount", false);
        mav.addObject("wrongemail", false);
        mav.addObject("created_account", false);
        mav.addObject("error", false);
        mav.addObject("id", id);
        if(login == 0) {
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
            id = "0";
            req.setEmail("0");
            System.out.println("계정생성 = " + id);
            mav.setViewName("home");
            return mav;
        } else {
            mav.setViewName("home");
        }
        id = "0";
        System.out.println("나중id22 = " + id);
        if (login == 1 && delaccount == 0) {//로그아웃
            login = 0;
            if(login == 0) {
                mav.addObject("login", 0);
            } else {
                mav.addObject("login", 1);
            }

            System.out.println("로그아웃 = " + login);
            MemberLogout lgo = ctx.getBean("lgo", MemberLogout.class);

            mav.addObject("logout", true);
            lgo.logout();
            session.invalidate();
        }
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
    public ModelAndView main(Model model, String id, HttpServletRequest request,
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
        session.setAttribute("idid", id);
        delaccount = 0;
        model.addAttribute("userid", userid2);
        System.out.println("id = " + id);
        System.out.println("delaccount = " + delaccount);
        System.out.println("delmemo = " + delmemo);
        System.out.println("editaccount = " + editaccount);
        if(login == 1) {
            mav.addObject("id2", id);
        }

        /*if (id == null) {
            id = "0";
        }*/
        String idid = (String) session.getAttribute("idid");
        if (login == 0) { //이전에 로그인 한적이 없을때
            System.out.println("MemberLogin.loginEmail = " + MemberLogin.loginEmail);
            try {
                MemberLogin lgn = ctx.getBean("lgn", MemberLogin.class);
                lgn.login(id, pwd); //로그인

                /*String name = (String)session.getAttribute("id");
                System.out.println("ididid = " + name);*/

                /*if(id.equals(name)) {
                    System.out.println("이미 로그인한 아이디");
                }*/
                mav.addObject("login", 1);
                System.out.println("login = " + login);
                System.out.println("id = " + id + ", pwd = " + pwd);
                userid2 = MemberLogin.loginEmail;
                userNickname = nickname;
                model.addAttribute("userid", userid2);
                login = 1; //로그인을했을때

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
    public ModelAndView findpwd(Model model, String id, String tel) {
        ModelAndView mav = new ModelAndView();
        List<Member> results2 = memberDao.findpwd(id, tel);
        model.addAttribute("result", results2);
        mav.setViewName("resultfindpwd");
        return mav;
    }



    //윤수명 고객문의 컨트롤러1---------
    @RequestMapping("/custom")
    public String handleStep1() {

    	return "custom";
    }

    @RequestMapping("/customwrite")
   	public String handleStep2(Model model) {
   		model.addAttribute("customrequest", new CustomRequest());
   		return "customwrite";
   	}

    @GetMapping(value = "/customchange/{count}")
    public String change(@PathVariable("count") Long memCount, Model model) {
		Custom custom1 = customdao.selectByCount(memCount);
	
		model.addAttribute("custom1", custom1);
		return "customchange";
	}
     
       
    @RequestMapping("/customchangeok")
    public String handleStep5(CustomRequest req) {
    	//customdao.update(req);
		return "customchangeok";
    	
   	    }
    
  

    /*
    @RequestMapping("/customchangeok/{count}")
   	public String  changecustom(@PathVariable("count") Long memCount, Model model)  {
    		Custom custom = customdao.selectByCount(memCount);
   			customdao.update(custom);
   			return "customdeleteok";
   
   	}*/
  
    
    @GetMapping(value = "/custom")
   	public String list(Model model) {
   		List<Custom> questionlist = customdao.selectAll();
   		model.addAttribute("QuestionList",questionlist);
   		return "custom";
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
	
	
    //윤수명끝----------------------------
    @GetMapping("/lobby")
    public ModelAndView lobby_start(Model model) {
        ModelAndView mv = new ModelAndView();
        model.addAttribute("Room_list", a.getRoom_list());
        a.create("test");
        mv.setViewName("lobby");
        return mv;
    }

    @GetMapping("/join")
    public ModelAndView lobby_join(Model model, @RequestParam(value = "id", required = false) String ID) {
        ModelAndView mv = new ModelAndView();
        model.addAttribute("id", ID);
        mv.setViewName("room");
        return mv;
    }
}
