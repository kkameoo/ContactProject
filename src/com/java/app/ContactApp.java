package com.java.app;

import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.java.dao.CallRecordDao;
import com.java.dao.EmploymentDao;
import com.java.dao.MessageRecordDao;
import com.java.dao.impl.CallRecordImpl;
import com.java.dao.impl.EmploymentImpl;
import com.java.dao.impl.MessageRecordImpl;
import com.java.vo.CallRecordVo;
import com.java.vo.EmploymentVo;
import com.java.vo.MessageRecordVo;

public class ContactApp {

	public static void main(String[] args) {
//		listEmployment();
//		searchEmployment();
		boolean flag = false;
		Scanner scanner = new Scanner(System.in);
		while (true) {
			
			if (flag) {
				System.out.println("프로그램을 종료합니다.");
				 break;
			}
			
			System.out.println("-----연락처 프로그램-----");
			System.out.println("번호를 선택하세요.");
			System.out.println("0.종료 \n1.전체목록 \n2.검색 \n3.지사별 열람 \n5.통화\t 6.통화 기록 \n7.메세지 송신\t 8.메세지 기록 ");
			int num = scanner.nextInt();
			
			switch(num) {
				case 0 : {
					flag = true;
					break;
				}
			
				case 1 : {
					listEmployment();
				}
				case 2 : {
					searchEmployment();
				}
				case 5 : {
					calledEmployment();
					break;
				}
				case 6 : {
					callRecord();
					while (true) {
						System.out.println("뒤로가기 0번");
						System.out.print(">");
						int numb = scanner.nextInt();
						if (numb == 0) {
							break;
						}
					}
					break;
				}
				case 7 : {
					searchEmployment();
					messageSend();
					break;
				}
				case 8 : {
					
					while (true) {
						listMessage();
						System.out.println("뒤로가기 0번\nid 선택");
						System.out.print(">");
						Long numb = scanner.nextLong();
						if (numb == 0) {
							break;
						} else {
							messageReadUpdate(numb);
							getMessage(numb);
							while (true) {
								System.out.println("뒤로가기 0번");
								System.out.print(">");
								Long numc = scanner.nextLong();
								if (numc == 0) {
									break;
								}
							}
						}
					}
					break;
				}
			}
		}
		scanner.close();
	}
	

	private static void listEmployment() {
		EmploymentDao dao = new EmploymentImpl();
		List<EmploymentVo> list = dao.getList();
		Iterator<EmploymentVo> iter = list.iterator();
		
		System.out.println("==========모든 연락처==========");
		
		while (iter.hasNext()) {
			EmploymentVo vo = iter.next();
			System.out.printf("id : %d\t    이름 : %s\t    나이 : %s\t 직급 : %s\n", vo.getId(), vo.getName(), vo.getAge(), vo.getEmployeeRank());
		}
		
		System.out.println("====================");
	}
	
	private static void searchEmployment() {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("검색어 입력: ");
		String keywordName = scanner.next();
		
		EmploymentDao dao = new EmploymentImpl();
		List<EmploymentVo> list = dao.search(keywordName);
		Iterator<EmploymentVo> iter = list.iterator();
		
		System.out.println("==========검색된 연락처==========");
		while (iter.hasNext()) {
			EmploymentVo vo = iter.next();
			System.out.printf("id : %d\t    이름 : %s\t    나이 : %s\t 직급 : %s\n", vo.getId(), vo.getName(), vo.getAge(), vo.getEmployeeRank());
		}
	}
	
	private static void calledEmployment() {
		Scanner scanner = new Scanner(System.in);
		CallRecordDao dao = new CallRecordImpl();
		int pn = scanner.nextInt();
		dao.call((long) pn);
		while (true) {
			System.out.println("통화중...");
			System.out.println("0 -> 통화종료");
			System.out.print(">");
			int num = scanner.nextInt();
			if (num == 0) {
				break;
			}
		}
		dao.callDown();
		System.out.println("통화종료");
	}
	
	private static void callRecord() {
		
		CallRecordDao dao = new CallRecordImpl();
		List<CallRecordVo> list = dao.getList();
		Iterator<CallRecordVo> iter = list.iterator();
		
		System.out.println("==========최근 통화 기록==========");
		
		while (iter.hasNext()) {
			CallRecordVo vo = iter.next();
			String startTime = vo.getStartDateTime().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH시mm분ss초"));
			String endTime = vo.getEndDateTime().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH시mm분ss초"));
			if (vo.getTotalTime() >= 60) {
				int minute = vo.getTotalTime()/60;
				int second = vo.getTotalTime()%60;
				System.out.printf("id : %d\t    사원번호 : %d\t    사원이름 : %s\t 	발신시간 : %s\t 종료시간 : %s\t 통화시간 : %d분 %d초\n",
						vo.getId(), vo.getEmployeeId(), vo.getEmployeeName(), startTime, endTime, minute, second);
			} else {
				System.out.printf("id : %d\t    사원번호 : %d\t    사원이름 : %s\t 	발신시간 : %s\t 종료시간 : %s\t 통화시간 : %d초\n",
						vo.getId(), vo.getEmployeeId(), vo.getEmployeeName(), startTime, endTime, vo.getTotalTime());
			}
			
		}
		
		System.out.println("=========================");
	}
	
	private static void messageSend() {
		Scanner scanner = new Scanner(System.in);
		MessageRecordDao dao = new MessageRecordImpl();
		System.out.println("사원 번호를 입력하세요.");
		Long id = scanner.nextLong();
		System.out.println("전달할 메시지를 입력하세요.");
		String message = scanner.next();
		dao.send(message, id);
		System.out.println("메시지가 전달되었습니다.");
	}
	
	private static void listMessage() {
		MessageRecordDao dao = new MessageRecordImpl();
		List<MessageRecordVo> list = dao.getList();
		Iterator<MessageRecordVo> iter = list.iterator();
		while(iter.hasNext()) {
			MessageRecordVo vo = iter.next();
			System.out.printf("id : %d\t    사원번호 : %d\t    이름 : %s\t 보낸 시간 : %s\t 읽음 상태 : %d\n", 
					vo.getId(), vo.getEmployeeId(), vo.getEmployeeName(), vo.getRegDate(), vo.getReadCheck());
		}
	}
	
	private static void getMessage(Long id) {
		MessageRecordDao dao = new MessageRecordImpl();
		MessageRecordVo vo = dao.getVo(id);
		System.out.printf("id : %d\n사원번호 : %d\t 이름 : %s\t 전화번호 : %s\t 송신시간 : %s\n메시지 : %s\n ",
				vo.getId(), vo.getEmployeeId(), vo.getEmployeeName(), vo.getEmployeeNumber(),
				vo.getRegDate(), vo.getMessageComment());
	}
	
	private static void messageReadUpdate(Long id) {
		MessageRecordDao dao = new MessageRecordImpl();
		dao.readUpdate(id);
	}
	
}