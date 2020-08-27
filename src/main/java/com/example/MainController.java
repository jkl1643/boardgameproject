package com.example;

//import com.sun.org.apache.xpath.internal.operations.Mod;
import com.example.Dao.Game;
import com.example.Dao.Purchase;

import org.apache.catalina.Server;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import custom_asking.Custom;
import custom_asking.CustomChange;
import custom_asking.CustomDao;
import custom_asking.CustomRequest;
import custom_asking.CustomWrite;


import MyGameRecord.MyGameRecord;
import MyGameRecord.MyGameRecordDao;
import MyGameRecord.MyGameRecordRequest;
import MyGameRecord.MyGameRecordWrite;

import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
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
    public static Hashtable loginUsers = new Hashtable();
    private DBcontroller control = (DBcontroller)ctx.getBean("dbcontrol");

    @Autowired
    private HashMap<Integer, Main_Server> Server_list;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private CustomWrite customwrite;

    @Autowired
    private CustomDao customdao;

    @Autowired
    private CustomChange customchange;

    @Autowired
    private MyGameRecordDao mygamerecorddao;

    @Autowired
    private MyGameRecordWrite mygamerecordwrite;
    private Room room;

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

    public void setMyGameRecordDao(MyGameRecordDao mygamerecorddao) {
		this.mygamerecorddao = mygamerecorddao;
	}

    public void setMygameRecordWrite(MyGameRecordWrite mygamerecordwrite) {
    	this.mygamerecordwrite = mygamerecordwrite;
    }

    @RequestMapping("/editaccount2")
    public ModelAndView editaccount2(ModelAndView mav, Model model, String id, String saveId,
                                    String oldpwd, String pwd, String pwd2, String nickname) {
        mav.addObject("unknown_email", false);
        mav.addObject("email_pwd_match", false);
        mav.addObject("email_pwd_match2", false);
        mav.addObject("logout", false);
        mav.addObject("delaccount", false);
        mav.addObject("wrongemail", false);
        mav.addObject("created_account", false);
        mav.addObject("error", false);
        model.addAttribute("Rank_list", control.GameRank_list());
        model.addAttribute("Rank_count", control.GameCount_list());
        mav.addObject("users", loginUsers.size());
        mav.addObject("loginduplicate", false);
        mav.addObject("login", 1);
        mav.addObject("editaccount", false);
        mav.addObject("chkpwd", false);
        mav.addObject("currentpwd", false);

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
            mav.addObject("chkpwd", true);
            editaccount = 0;
        } catch (PasswordNotMatchException2 e) {
            System.out.println("현재 비밀번호가 일치하지 않습니다.");
            mav.addObject("currentpwd", true);
            editaccount = 0;
        }

        mav.setViewName("home");
        return mav;
    }

	@RequestMapping("/logout")
    public ModelAndView logout(ModelAndView mav, HttpSession session, Model model) {
        mav.addObject("unknown_email", false);
        mav.addObject("email_pwd_match", false);
        mav.addObject("email_pwd_match2", false);
        mav.addObject("logout", false);
        mav.addObject("delaccount", false);
        mav.addObject("wrongemail", false);
        mav.addObject("created_account", false);
        mav.addObject("error", false);
        model.addAttribute("Rank_list", control.GameRank_list());
        model.addAttribute("Rank_count", control.GameCount_list());
        mav.addObject("users", loginUsers.size());
        mav.addObject("loginduplicate", false);
        Member name = (Member)session.getAttribute("mem");
        login = 0;
        try {
            if (login == 0) {
                mav.addObject("login", 0);
            } else {
                mav.addObject("login", 1);
            }
            System.out.println("로그아웃 = " + login);

            MemberLogout lgo = ctx.getBean("lgo", MemberLogout.class);
            mav.addObject("loginduplicate", false);
            mav.addObject("logout", true);
            lgo.logout();
            Enumeration e = loginUsers.keys();
            while (e.hasMoreElements()) {
                String a = (String) e.nextElement();
                System.out.println("loginuser = " + loginUsers.get(a));
                if (loginUsers.get(a).equals(name.getEmail())) {
                    loginUsers.remove(name.getEmail());
                    System.out.println(" " + name.getEmail() + "해쉬 사라짐");
                    session.invalidate();
                }
            }
        } catch (Exception e){

        }
        mav.setViewName("home");
        return mav;
    }

    @RequestMapping("/home")
    public ModelAndView login(ModelAndView mav, HttpSession session, Model model,
                              @RequestParam(value = "EMAIL2", required = false, defaultValue = "0") String id,
                              @RequestParam(value = "PWD2", required = false) String pwd,
                              @RequestParam(value = "PWD22", required = false) String pwd2,
                              @RequestParam(value = "NICKNAME2", required = false) String nickname) {
        System.out.println("--------홈------------");
        mav.addObject("users", loginUsers.size());
        try {
            Member name = (Member)session.getAttribute("mem");
            login = 0;
        } catch (Exception e) {
            login = 1;
        }
        model.addAttribute("Rank_list", control.GameRank_list());
        model.addAttribute("Rank_count", control.GameCount_list());
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
        mav.addObject("editaccount", false);
        mav.addObject("chkpwd", false);
        mav.addObject("currentpwd", false);
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

        if (!id.equals("0")) { //회원가입 아아디에 값을 입력했을때
            if (!req.isPasswordEqualToConfirmPassword()) {
                mav.setViewName("home");
                mav.addObject("email_pwd_match", true);
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
                memberRegSvc.regist(req); //회원가입
                Member member2 = memberDao.selectByEmail(id);
                MyGameRecord newrecord = new MyGameRecord(0, 0, 0, 0, 1, member2.getId());
                mygamerecorddao.insert2(newrecord, session, req);
                mav.addObject("created_account", true);
            } catch (DuplicateMemberException e) {
                mav.addObject("error", true);
                System.out.println("이미 존재하는 이메일입니다.\n");
            } catch (Exception e) {
                mav.addObject("error", true);
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
        mav.addObject("currentpwd", false);
        mav.addObject("editaccount", false);
        mav.addObject("chkpwd", false);
        mav.addObject("created_memo", false);
        mav.addObject("error", false);
        mav.addObject("login", 0);
        mav.addObject("loginduplicate", false);
        model.addAttribute("Rank_list", control.GameRank_list());
        model.addAttribute("Rank_count", control.GameCount_list());
        mav.addObject("users", loginUsers.size());

        Member name2 = (Member)session.getAttribute("mem");
        Member member = memberDao.selectByEmail(id);
        MemberLogin lgn = ctx.getBean("lgn", MemberLogin.class);

        if(name2 == null){
            Enumeration en = loginUsers.keys();
            while (en.hasMoreElements()) {
                String key = en.nextElement().toString();
                System.out.println(key + " : " + loginUsers.get(key));
                if (key.equals(member.getEmail())) {
                    try {
                        lgn.login(id, pwd);
                        loginUsers.put(id, id);
                        session.setAttribute("mem", member);
                    } catch (Exception e){

                    }
                }
            }
        }
        session.setAttribute("idid", id);
        delaccount = 0;
        model.addAttribute("userid", id);

        System.out.println("delaccount = " + delaccount);
        System.out.println("delmemo = " + delmemo);
        System.out.println("editaccount = " + editaccount);

        if(login == 1) {
            mav.addObject("id2", id);
        }

        name2 = (Member)session.getAttribute("mem");
        String idid = (String) session.getAttribute("idid");
        if (name2 == null) { //이전에 로그인 한적이 없을때
            Member name = (Member) session.getAttribute("mem");
            System.out.println("name = " + name);

            Enumeration en = loginUsers.keys();
            while (en.hasMoreElements()) {
                String key = en.nextElement().toString();
                System.out.println(key + " : " + loginUsers.get(key));
                if (key.equals(member.getEmail())) {
                    mav.addObject("loginduplicate", true);
                    mav.setViewName("home");
                    return mav;
                }
            }

            System.out.println("해쉬테이블 인원 : " + String.valueOf(loginUsers.size()));
            mav.addObject("users", loginUsers.size());

            try {
                lgn.login(id, pwd); //로그인
                loginUsers.put(id, id);
                session.setAttribute("mem", member);
                session.setMaxInactiveInterval(10);
                mav.addObject("login", 1);
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
        } else {
            mav.setViewName("home");
        }
        mav.setViewName("home");
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
            System.out.println("아디 = " + delid + "비번 = " + delpwd);
            try {
                checkidpwd.checkidpassword(delid, delpwd);
                MemberDao memberDao = ctx.getBean("memberDao", MemberDao.class);
                memberDao.delete(delid, delpwd);
                mav.addObject("delaccount", true);
                login = 0;
                delaccount = 0;
                mav.setViewName("home");
            } catch (MemberNotFoundException e) {
                mav.setViewName("delaccount");
                mav.addObject("wrongemail", true);
                System.out.println("잘못된 아이디 입력입니다.");
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
    public ModelAndView findpwd(Model model, String id, String nickname) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("findpwd");
        return mav;
    }

    @RequestMapping("/resultfindpwd")
    public ModelAndView resultfindpwd(Model model, String id, String nickname) {
        ModelAndView mav = new ModelAndView();
        List<Member> results2 = memberDao.findpwd(id, nickname);
        model.addAttribute("result", results2);
        Member member = memberDao.selectByEmail(id);
        try {
            mav.addObject("realemail", member.getEmail());
        } catch (Exception e){
            mav.addObject("realemail", "0");
        }
        mav.addObject("inputid", id);

        try {
            mav.addObject("realnickname", member.getNickname());
        } catch (Exception e){
            mav.addObject("realnickname", "/*987/");
        }
        mav.addObject("inputnickname", nickname);
        mav.setViewName("resultfindpwd");
        return mav;
    }

    @RequestMapping("/gamescreen")
    public ModelAndView gamescreen(Model model) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("gamescreen");
        return mav;
    }

    //고객문의 컨트롤러1---------
    @RequestMapping("/custom")
    public String handleStep1() {
    	return "custom";
    }

    @RequestMapping(value = "/record")
    public String myresult(Model model, Long memnum) {
    	MyGameRecord record = mygamerecorddao.selectByMEMNUM(memnum);
    	model.addAttribute("myrecord", record);
		return "mygamerecord";

    }
    
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

    @GetMapping(value = "/customchange")
    public String change(@RequestParam(value = "count", required = true) Long memCount, Model model) {
		Custom custom1 = customdao.selectByCount(memCount);
		model.addAttribute("custom1", custom1);
		return "customchange";
	}
       
    @RequestMapping("/customchangeok") // 수정함 병렬
    public String handleStep5(Model model, Long count1, String title1, String content1) {
        customchange.changedata(count1, title1, content1);
    	return "customchangeok";
    }

 
    @GetMapping(value = "/custom")
   	public String list(Model model) {
   		List<Custom> questionlist = customdao.selectAll();
   		model.addAttribute("QuestionList",questionlist);
   		return "custom";
   	}

    @GetMapping(value = "/content")
    public String detail(@RequestParam(value = "count", required = true) Long memCount, Model model) {
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

    @RequestMapping("/testok")
    public String handleStep8(MyGameRecordRequest request, HttpSession session) {
        mygamerecordwrite.input(request, session);
        // customchange.changedata(count1, title1, content1);
        return "customchangeok";
    }

    @RequestMapping("/test")
    public String handleStep8(Model model) {
        model.addAttribute("mygamerecord", new MyGameRecordRequest());
        return "test";
    }

    @GetMapping("/gamelobby")
    public ModelAndView Gamelobby(Model model, HttpSession session, HttpServletResponse response,
                                   @RequestParam(value = "gamenumber", required = true) int game_number) throws IOException {
        ModelAndView mv = new ModelAndView();
        if(session.getAttribute("idid") == null) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인이 필요합니다.'); location.href='home';</script>");
            out.flush();
            return mv;
        }

        int key = control.keyBynick((String) session.getAttribute("idid"));
        HashMap keyset = new HashMap<String, Integer>();
        keyset.put("game", game_number);
        keyset.put("member", key);

        if(control.Checkingbuy(keyset) == 0) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('게임구매가 필요합니다.'); location.href='gamerank';</script>");
            out.flush();
            return mv;
        }

        model.addAttribute("Stat", control.Statbynick(keyset));
        model.addAttribute("Game", control.Selectbykey(game_number));
        session.setAttribute("Gamenumber", game_number);
        mv.setViewName("Game_lobby");
        return mv;
    }

    @RequestMapping(value="/createroom", method = {RequestMethod.POST})
    public ModelAndView CreateRoom(Model model, HttpSession session,
                                   @RequestParam(value = "Createroomname", required = true) String name,
                                   @RequestParam(value = "Createroomgame", required = true) String game,
                                   @RequestParam(value = "Createroompw", defaultValue = "", required = false) String pw) {
        Main_Server Server = Server_list.get(control.Selectbyname(game).getGame_number());
        String Roomid = Server.create(name, game, pw);
        session.setAttribute("room", room);
        return lobby_join(model, session, Roomid, pw);
    }

    @GetMapping("/refreshgamelist")
    public ModelAndView RefreshGamelist(Model model, HttpSession session) {
        Main_Server Server = Server_list.get((int) session.getAttribute("Gamenumber"));

        ModelAndView mv = new ModelAndView();
        model.addAttribute("Room_list", Server.getRoom_list().values());
        mv.setViewName("Game_roomlist"); // room 만든후 .
        return mv;
    }

    @PostMapping("/join")
    public ModelAndView lobby_join(Model model, HttpSession session, @RequestParam(value = "joinid", required = false) String ID,
                                   @RequestParam(value = "joinpw", required = false) String PW) {
        ModelAndView mv = new ModelAndView();

        Main_Server Server = Server_list.get((int) session.getAttribute("Gamenumber"));

        if(Server.getRoom_list().get(ID).getPassword().equals(PW)) {
            if(Server.getRoom_list().get(ID).getMaxplayer() == Server.getRoom_list().get(ID).getPlayer()) {
                mv.setViewName("Game_lobby");
                return mv;
            }
            Server.select(ID, PW, (String) session.getAttribute("idid"));
            System.out.println("방 접속 : " + session.getAttribute("idid") + " / " + ID);

            model.addAttribute("id", ID);
            model.addAttribute("pw", PW);
            switch(Server.getRoom_list().get(ID).getGame()) {
                case "Yahtzee":
                    mv.setViewName("gamescreen");
                    break;
            }
            mv.setViewName("gamescreen");
        }
        else
            mv.setViewName("Game_lobby");
        return mv;
    }

    @GetMapping("/refreshuserlist")
    public ModelAndView RefreshUserlist(Model model, HttpSession session) {
        ModelAndView mv = new ModelAndView();

        Main_Server Server = Server_list.get((int) session.getAttribute("Gamenumber"));

        model.addAttribute("User_list", Server.getUser_nick().keySet());
        mv.setViewName("Game_userlist"); // room 만든후 .
        return mv;
    }

    @GetMapping("/gamerank")
    public ModelAndView Gamerank(Model model) {
        ModelAndView mv = new ModelAndView();
        System.out.println(control.GameRank_list());
        model.addAttribute("Rank_list", control.GameRank_list());
        model.addAttribute("Rank_count", control.GameCount_list());
        mv.setViewName("Game_rank");
        return mv;
    }

    @GetMapping("/gameinfo")
    public ModelAndView Gameinfo(Model model, HttpSession session, HttpServletResponse response,
                                 @RequestParam(value = "game", required = true) int game_number) throws IOException {
        ModelAndView mv = new ModelAndView();

        if(session.getAttribute("idid") == null) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인이 필요합니다.'); location.href='home';</script>");
            out.flush();
            return mv;
        }
        System.out.println("안녕하세요"+control.keyBynick((String) session.getAttribute("idid")));
        int key = control.keyBynick((String) session.getAttribute("idid"));
        HashMap keyset = new HashMap<String, Integer>();
        keyset.put("game", game_number);
        keyset.put("member", key);

        model.addAttribute("Checking", control.Checkingbuy(keyset));
        model.addAttribute("Game", control.Selectbykey(game_number));
        mv.setViewName("Game_info");
        return mv;
    }

    @GetMapping("/buygame")
    public ModelAndView Buygame(Model model, HttpSession session,
                                 @RequestParam(value = "gamenumber", required = true) int game_number) {
        ModelAndView mv = new ModelAndView();

        LocalDateTime Today = LocalDateTime.now().plusHours(9);
        int key = control.keyBynick((String) session.getAttribute("idid"));

        Purchase buy = new Purchase(key, game_number, Today);
        control.Buygame(buy);

        HashMap keyset = new HashMap<String, Integer>();
        keyset.put("game", game_number);
        keyset.put("member", key);
        model.addAttribute("Checking", control.Checkingbuy(keyset));
        model.addAttribute("Game", control.Selectbykey(game_number));
        mv.setViewName("Game_info");
        return mv;
    }

    @GetMapping("/mygamelist")
    public ModelAndView MyGamelist(Model model, HttpSession session) {
        ModelAndView mv = new ModelAndView();
        int key = control.keyBynick((String) session.getAttribute("idid"));
        model.addAttribute("My_list", control.Game_mylist(key));
        mv.setViewName("Game_mylist");
        return mv;
    }

    @RequestMapping("/naver")
    public ModelAndView naver(ModelAndView mav) {
        mav.setViewName("naver");
        return mav;
    }

}