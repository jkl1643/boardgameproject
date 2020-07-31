package com.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainForSpring {
	static int i = 1;

	private static AbstractApplicationContext ctx = null;
	public static int state = 0; //로그인, 로그아웃 상태
	public static void main(String[] args) throws IOException{
		//ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			PrintStream console = System.out;
			System.setOut(console);
			System.out.println("명령어를 입력하세요.");
			
			if(state == 0) { // 도움말
				printHelpBeforeLogin();
			} else
				printHelpAfterLogin();
			
			String command = reader.readLine();
			if (state == 0) { // 로그인 하지 않았을 때				
				if (command.equalsIgnoreCase("exit")) {
					System.out.println("종료합니다");
					break;
				}
				if (command.startsWith("new ")) {
					processNewCommand(command.split(" "));
					continue;
				} else if (command.startsWith("login ")) {
					processLoginCommand(command.split(" "));
					continue;
				} else if (command.equals("help")) {
					printHelpBeforeLogin();
					continue;
				} else if (command.startsWith("date ")) {
					processDateCommand(command.split(" "));
					continue;
				}
			}

			if (state == 1) {
				if (command.startsWith("change ")) {
					processChangeCommand(command.split(" "));
					continue;
				} else if (command.equals("info")) {
					processInfoCommand();
					continue;
				} else if (command.startsWith("memo ")) {
					processMemoCommand(command.split(" "));
					continue;
				} else if (command.startsWith("memodate ")) {
					processMemoDateCommand(command.split(" "));
					continue;
				} else if (command.equals("logout")) {
					processLogoutCommand();
					continue;
				} else if (command.equals("help")) {
					printHelpAfterLogin();
					continue;
				} else if (command.startsWith("delaccount ")) {
					processDelAccountcommand(command.split(" "));
					continue;
				} else if (command.startsWith("delmemo ")) {
					processDelMemocommand(command.split(" "));
					continue;
				}
			}
			printError();
		}
	}
	
	private static void processNewCommand(String[] arg) {
		if (arg.length != 7) {
			printError();
			return;
		}
		MemberRegisterService regSvc = ctx.getBean("memberRegSvc", MemberRegisterService.class);
		RegisterRequest req = new RegisterRequest();
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		req.setTel(arg[5]);
		req.setAddress(arg[6]);
		
		if(!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("암호와 확인이 일치하지 않습니다.\n");
			return;
		}
		try {
			regSvc.regist(req);
			System.out.println("등록했습니다.\n");
		} catch (DuplicateMemberException e) {
			System.out.println("이미 존재하는 이메일입니다.\n");
		} 
	}
	
	private static void processDelAccountcommand(String[] arg) {
		if(arg.length != 3) {
			printError();
			return;
		}		
		MemberDao memberDao = ctx.getBean("memberDao", MemberDao.class);
		memberDao.delete(arg[1], arg[2]);
	}
	
	private static void processDelMemocommand(String[] arg) {
		if(arg.length != 4) {
			printError();
			return;
		}
		MemoDao memoDao = ctx.getBean("memoDao", MemoDao.class);
		memoDao.delete(arg[1], arg[2], arg[3]);
	}
	
	private static void processMemoCommand(String[] arg) {
		if (arg.length != 7 || !arg[1].equals(MemberLogin.loginEmail)) {
			printError();
			return;
		}
		MemoRegisterService regSvc2 = ctx.getBean("memoRegSvc", MemoRegisterService.class);		
		MemoRegisterRequest req2 = new MemoRegisterRequest();
		req2.setEmail(arg[1]);
		req2.setYear(arg[2]);
		req2.setMonth(arg[3]);
		req2.setDay(arg[4]);
		req2.setMemo(arg[5]);
		req2.setSaveImagePath(arg[6]);
		try {
			regSvc2.regist2(req2);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		/*if (MemoDao.nextId2 != 0) { //메모목록에 메모가 있을때
			for (i = 1; i < MemoDao.nextId2 + 1; i++) {
				q = 1;
				MemoSelectById memoSelectById = ctx.getBean("memoSelectById", MemoSelectById.class);
				Memo memo = memoSelectById.memoSelectById();
				if (memo.getId() == null) {
					return;
				}
				if (memo.getEmail().equals(arg[1]) && memo.getYear().equals(arg[2]) && memo.getMonth().equals(arg[3])
						&& memo.getDay().equals(arg[4])) { //같은 날짜에 이미 메모가 있을때
					ChangeMemoService changememoSvc = ctx.getBean("changememoSvc", ChangeMemoService.class);
					changememoSvc.changeMemo(arg[1], arg[2], arg[3], arg[4], arg[5], arg[6]); //이미 있는걸 바꿈
//					try {
//					File file = new File(memo.getSavePath() + "/Memo.txt");
//					PrintStream printStream = new PrintStream(new FileOutputStream(file));
//					PrintStream sysout = System.out;
//					System.setOut(printStream);
//					memosavelistPrinter.printAll2();
//					System.setOut(sysout);
//					printStream.close();
//					} catch (Exception e) {
//						
//					}
				} else { //그 날짜에 메모가 없을때 새로 등록
					req2.setEmail(arg[1]);
					req2.setYear(arg[2]);
					req2.setMonth(arg[3]);
					req2.setDay(arg[4]);
					req2.setMemo(arg[5]);
					req2.setSaveImagePath(arg[6]);
//					if (arg.length == 8)
//						req2.setSaveImagePath(arg[7]);
					try {
						regSvc2.regist2(req2);
						ReadFile readFile = ctx.getBean("readFile", ReadFile.class);
						if (arg.length == 7)
							readFile.readImage(arg[6]);
						System.out.println("메모 등록했습니다.\n");
//						File file = new File(arg[6] + "/Memo.txt");
//						PrintStream printStream = new PrintStream(new FileOutputStream(file));
//						PrintStream sysout = System.out;
//						System.setOut(printStream);
//						memosavelistPrinter.printAll2();
//						System.setOut(sysout);
//						printStream.close();
						if (q == 1) {
							break;
						}
					} catch (MemberNotFoundException e) {
						System.out.println("존재하지 않는 이메일입니다.\n");
					} catch (WrongIdPasswordException e) {
						System.out.println("메모가 이미 존재합니다.");
					} 
				}
			}
		} else { //메모목록에 메모가 없을때
			req2.setEmail(arg[1]);
			req2.setYear(arg[2]);
			req2.setMonth(arg[3]);
			req2.setDay(arg[4]);
			req2.setMemo(arg[5]);
			req2.setSaveImagePath(arg[6]);
//			if (arg.length == 8)
//				req2.setSaveImagePath(arg[7]);
			try {
				regSvc2.regist2(req2);
				System.out.println("메모 등록했습니다.\n");
//				File file = new File(arg[6] + "/Memo.txt");
//				PrintStream printStream = new PrintStream(new FileOutputStream(file));
//				PrintStream sysout = System.out;
//				System.setOut(printStream);
//				memosavelistPrinter.printAll2();
//				System.setOut(sysout);
//				printStream.close();
			} catch (MemberNotFoundException e) {
				System.out.println("존재하지 않는 이메일입니다.\n");
			} catch (WrongIdPasswordException e) {
				System.out.println("메모가 이미 존재합니다.");
			}
		}*/
	}
	
	private static void processLoginCommand(String[] arg) { //로그인
		if(arg.length != 3) {
			printError();
			return;
		}
		MemberLogin lgn = ctx.getBean("lgn", MemberLogin.class);
		try {
			lgn.login(arg[1], arg[2]);
		} catch (MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일입니다.\n");
		} catch (WrongIdPasswordException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다.\n");
		} catch (IOException e) {
			
		}
	}
	
	private static void processLogoutCommand() {
		MemberLogout lgo = ctx.getBean("lgo", MemberLogout.class);
		lgo.logout();
	}
	
	private static void processChangeCommand(String[] arg) {
		if(arg.length != 7 || !arg[1].equals(MemberLogin.loginEmail)) {
			printError();
			return;
		}
		ChangeInfoService changeInfoSvc = ctx.getBean("changeInfoSvc", ChangeInfoService.class);
		try {
					changeInfoSvc.changePassword(arg[1], arg[2], arg[3], arg[4], arg[5], arg[6]);
			System.out.println("정보를 수정했습니다.\n");
		} catch (MemberNotFoundException e) {
			System.out.println("존재하지 않는 이메일입니다.\n");
		} catch (WrongIdPasswordException e) {
			System.out.println("이메일과 암호가 일치하지 않습니다.\n");
		}
	}
	
	private static void processInfoCommand() {
		MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter", MemberInfoPrinter.class);		
		infoPrinter.printMemberInfo();
	}

	private static void processDateCommand(String[] arg) { // 달력 출력
		if (arg.length != 3) {
			printError();
			return;
		}
		PrintCalendar printCal = ctx.getBean("printCal", PrintCalendar.class);
		printCal.printDate(arg[1], arg[2]);// 년 월
	}
	
	private static void processMemoDateCommand(String[] arg) { // 달력 출력
		if (arg.length < 3 || arg.length > 4) {
			printError();
			return;
		}
		MemoCalendar memoCal = ctx.getBean("memoCal", MemoCalendar.class);
		if(arg.length == 3) {
			memoCal.memberDate(arg[1], arg[2]);// 년 월
		}
		else if(arg.length == 4) {
			MemoListPrinter memolistPrinter = ctx.getBean("memolistPrinter", MemoListPrinter.class);
			memolistPrinter.printMemo(arg[1], arg[2], arg[3]);
		}
			
	}
		
	private static void printHelpBeforeLogin() {
		System.out.println("  도 움 말    : help");
		System.out.println(" ID 생성   : new ID 이름 비밀번호 비밀번호확인 전화번호 주소");
		//System.out.println("  계정 목록   : list");
		System.out.println("  로  그 인    : login ID 비밀번호");
		System.out.println("  달     력    : date YYYY M");
		System.out.println("");
	}
	
	private static void printHelpAfterLogin() {
		System.out.println("  도 움 말    : help");
		System.out.println("  정보 변경   : change ID 현재비밀번호 변경할비밀번호 변경할이름 변경할전화번호 변경할주소");
		System.out.println("  '-'입력시 정보 수정하지않음");
		System.out.println("  계정 정보   : info");
		System.out.println("  계정 삭제   : delaccount ID 비밀번호");
		System.out.println("  로그 아웃   : logout");
		System.out.println("  달     력    : memodate YYYY M or YYYY M D");
		System.out.println("  메     모    : memo ID YYYY M D 메모할내용 파일첨부경로+파일이름");
		System.out.println("  메모 삭제   : delmemo YYYY M D");
		System.out.println("");
	}
	
	private static void printError() {
		System.out.println("잘 못 입력했습니다.");
	}
}
