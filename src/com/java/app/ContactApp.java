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
import com.java.vo.DepartmentListVo;
import com.java.vo.EmploymentVo;
import com.java.vo.MessageRecordVo;
import com.java.vo.RankVo;
import com.java.vo.RegionsVo;

public class ContactApp {

	public static void main(String[] args) {
		boolean flag = false;
		Scanner scanner = new Scanner(System.in);
		while (true) {
			
			if (flag) {
				System.out.println("프로그램을 종료합니다.");
				 break;
			}
			
			System.out.println("-----연락처 프로그램-----");
			System.out.println("번호를 선택하세요.");
			System.out.println("0.종료 \n1.전체목록 \n2.검색 \n3.사원 정보 추가 "
					+ "\n4.목록  \n5.통화\t 6.통화 기록 \n7.메세지 송신\t 8.메세지 기록 ");
			
			int num = scanner.nextInt();
			
			switch(num) {
				case 0 : {
					flag = true;
					break;
				}
			
				case 1 : {
					while (true) {
						listEmployment();
						System.out.println("0.뒤로가기");
						System.out.println("-1.내림차순 보기");
						System.out.print("Id 입력 >");
						Long back = scanner.nextLong(); 
						if (back == 0) {
							break;
						} else if(back > 0) {
							while (true) {
								showDetail(back);
								System.out.println("0.뒤로가기");
								System.out.println("1.삭제");
								System.out.println("2.수정");
								System.out.print(">");
								int back2 = scanner.nextInt(); 
								if (back2 == 0) {
									break;
								} else if(back2 == 1) {
									deleteEmployment(back);
									break;
								} else if (back2 == 2) {
									while (true) {
									System.out.println("0. 뒤로가기");
									System.out.print("1. 이름 변경\t");
									System.out.println("2. 나이 변경");
									System.out.print("3. 전화번호 변경\t");
									System.out.println("4. 이메일 변경");
									System.out.print("5. 주소 변경\t");
									System.out.println("6. 부서 변경");
									System.out.print("7. 직급 변경\t");
									System.out.println("8. 연봉 변경");
									System.out.print(">");
									int col = scanner.nextInt();
									if (col == 0) {
										break;
									} 
									if(col > 0 && col < 9){
										scanner.nextLine();
										String data = scanner.nextLine();
										updateEmployment(col, data, back);
									} else {
										System.out.println("잘못된 입력");
									}
									}
								}
							}
							
						} else if(back == -1) {
							listEmploymentDesc();
							back();
						} else {
							System.out.println("잘못된 입력");
						}
					}
					break;
				}
				case 2 : {
					List<EmploymentVo> list = searchEmploymentByName();
					while (true) {
						for(EmploymentVo vo : list) {
						System.out.printf("id : %d\t    이름 : %s\t    나이 : %s\t 직급 : %s\n", vo.getId(), vo.getName(), vo.getAge(), vo.getEmployeeRank());
					}
						System.out.println("0.뒤로가기");
						System.out.print("Id 입력 >");
						Long back = scanner.nextLong(); 
						if (back == 0) {
							break;
						} else if(back > 0) {
							while (true) {
								showDetail(back);
								System.out.println("0.뒤로가기");
								System.out.println("1.삭제");
								System.out.println("2.수정");
								System.out.print(">");
								int back2 = scanner.nextInt(); 
								if (back2 == 0) {
									break;
								} else if(back2 == 1) {
									deleteEmployment(back);
									break;
								} else if (back2 == 2) {
									while (true) {
									System.out.println("0. 뒤로가기");
									System.out.print("1. 이름 변경\t");
									System.out.println("2. 나이 변경");
									System.out.print("3. 전화번호 변경\t");
									System.out.println("4. 이메일 변경");
									System.out.print("5. 주소 변경\t");
									System.out.println("6. 부서 변경");
									System.out.print("7. 직급 변경\t");
									System.out.println("8. 연봉 변경");
									System.out.print(">");
									int col = scanner.nextInt();
									if (col == 0) {
										break;
									} 
									if(col > 0 && col < 9){
										scanner.nextLine();
										String data = scanner.nextLine();
										updateEmployment(col, data, back);
									} else {
										System.out.println("잘못된 입력");
									}
									}
								}
							}
							
						} else if(back == -1) {
							listEmploymentDesc();
							back();
						} else {
							System.out.println("잘못된 입력");
						}
					}
					break;
					
				}
				case 3 : {
					insertEmployment();
					break;
				}
				
				case 4 : {

					while(true) {
						System.out.println("0.뒤로가기");
						System.out.println("1.부서별 목록");
						System.out.println("2.지사별 목록");
						System.out.println("3.직급별 목록");
						System.out.println("----------------");
						System.out.print(">");
						int numb = scanner.nextInt();
						if(numb == 0) {
							break;
						}
						if(numb == 1) {
							while(true) {
								System.out.println("1. 영업부\n2. 재료개발부\n3. 사업부\n4. 총무부\n5. 인사부\n6. 기획부" + "\n7. 회계부" + "\n8. 개발부"
										+ "\n9. 생산관리부" + "\n10. 전산 IT부" + "\n11. 교육상품부" + "\n12. 마케팅부" + "\n13. 품질관리부" + "\n14. 해외영업부");
								System.out.println("----------------");
								System.out.println("0.뒤로가기");
								System.out.print("ID 선택 >");
								Long dptId = scanner.nextLong();
								if (dptId == 0) {
									break;
								} else if (dptId > 0 && dptId < 15 ) {
									while (true) {
										listEmploymentByDepartment(dptId);
										back();
										break;
									}
								} else {
								 System.out.println("잘못된 입력");
								}
							}
						} else if (numb == 2) {
							while(true) {
								System.out.println("1. 부산\n2. 대구\n3. 인천\n4. 광주\n5. 대전\n6. 울산 \n7. 서울\n");
								System.out.println("----------------");
								System.out.println("0.뒤로가기");
								System.out.print("ID 선택 >");
								Long regId = scanner.nextLong();
								if (regId == 0) {
									break;
								} else if (regId > 0 && regId < 8) {
								while (true) {
									listEmploymentByRegion(regId);
									back();
									break;
								}
							} else {
								System.out.println("잘못된 입력");
							}
							}
							
						} else if (numb == 3) {
							
							while(true) {
								System.out.println("1. 사원\n2. 대리\n3. 과장\n4. 차장\n5. 부장\n");
								System.out.println("----------------");
								System.out.println("0.뒤로가기");
								System.out.print("ID 선택 >");
								Long regId = scanner.nextLong();
								if (regId == 0) {
									break;
								} else if(regId > 0 && regId < 6) {
								while (true) {
									listEmploymentByRank(regId.intValue());
									back();
									break;
								}
								} else {
									System.out.println("잘못된 입력");
								}
								
							}
						} else {
							System.out.println("잘못된 입력");
						}
					}
					break;			
				}
				
				case 5 : {
					while (true) {
						searchEmploymentByNameVoid();
						System.out.println("통화할 사원의 사원번호를 입력하세요.");
						System.out.println("0.뒤로가기");
						System.out.print(">");
						int back = scanner.nextInt(); 
						if (back == 0) {
							break;
						} else {
							called(back);
							break;
						}
					}
					break;
				}
				case 6 : {
					calledRecord();
					back();
					break;
				}
				case 7 : {
					while (true) {
						searchEmploymentByNameVoid();
						System.out.println("문자할 사원의 사원번호를 입력하세요.");
						System.out.println("0.뒤로가기");
						System.out.print(">");
						Long back = scanner.nextLong(); 
						if (back == 0) {
							break;
						} else {
							sendMessage(back);
							break;
						}
					}
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
							updateMessageRead(numb);
							getMessage(numb);
							while (true) {
								System.out.println("0.뒤로가기");
								System.out.print("Id 입력 >");
								Long numc = scanner.nextLong();
								if (numc == 0) {
									break;
								}
							}
						}
					}
					break;
				} default : {
					System.out.println("잘못된 입력");
				}
			}
		}
		scanner.close();
	}
	
	// 모든 사원 연락처
	private static void listEmployment() {
		EmploymentDao dao = new EmploymentImpl();
		List<EmploymentVo> list = dao.getList();
		Iterator<EmploymentVo> iter = list.iterator();
		
		System.out.println("===================== 모든 연락처 =====================");
		while (iter.hasNext()) {
			EmploymentVo vo = iter.next();
			System.out.printf("id : %d\t    이름 : %s\t    나이 : %s\t 직급 : %s\n", vo.getId(), vo.getName(), vo.getAge(), vo.getEmployeeRank());
		}
		System.out.println("==============================================================");
	}
	
	// 모든 사원 연락처 내림차순
		private static void listEmploymentDesc() {
			EmploymentDao dao = new EmploymentImpl();
			List<EmploymentVo> list = dao.getListDesc();
			Iterator<EmploymentVo> iter = list.iterator();
			
			System.out.println("===================== 모든 연락처 =====================");
			while (iter.hasNext()) {
				EmploymentVo vo = iter.next();
				System.out.printf("id : %d\t    이름 : %s\t    나이 : %s\t 직급 : %s\n", vo.getId(), vo.getName(), vo.getAge(), vo.getEmployeeRank());
			}
			System.out.println("==============================================================");
		}
	
	// 이름으로 연락처 검색
	private static List<EmploymentVo> searchEmploymentByName() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("검색할 이름 : ");
		String keywordName = scanner.next();
		
		EmploymentDao dao = new EmploymentImpl();
		List<EmploymentVo> list = dao.search(keywordName);
		return list;
	}
	
	// 이름으로 연락처 검색 반환값 보이드
	private static void searchEmploymentByNameVoid() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("검색할 이름 : ");
		String keywordName = scanner.next();
		
		EmploymentDao dao = new EmploymentImpl();
		List<EmploymentVo> list = dao.search(keywordName);
		Iterator<EmploymentVo> iter = list.iterator();
		while (iter.hasNext()) {
			EmploymentVo vo = iter.next();
			System.out.printf("id : %d\t    이름 : %s\t    나이 : %s\t 직급 : %s\n", vo.getId(), vo.getName(), vo.getAge(), vo.getEmployeeRank());
		}
	}
	
	// 통화
	private static void called(int pn) {
		Scanner scanner = new Scanner(System.in);
		CallRecordDao dao = new CallRecordImpl();
		
		boolean result = dao.call((long) pn);
		if (result == false) {
			System.out.println("잘못된 입력입니다.");
			return;
		} else {
			System.out.println("사원 번호 : " + pn);
		}
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
	
	// 통화 기록
	private static void calledRecord() {
		
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
	}
	
	
	// 사원 추가
	private static void insertEmployment() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("이름 : ");
		String name = scanner.next();
		System.out.print("나이 : ");
		String age = scanner.next();
		System.out.print("전화번호 : ");
		String number = scanner.next();
		System.out.print("이메일 : ");
		String email = scanner.next();
		System.out.print("주소 : ");
		String address = scanner.next();
		System.out.print("부서 id : ");
		Long departmentId = scanner.nextLong();
		System.out.print("직급 : ");
		String employeeRank = scanner.next();
		System.out.print("월급 : ");
		Long salary = scanner.nextLong();
		
		EmploymentVo vo = new EmploymentVo(name, age, number, email, address, departmentId, employeeRank, salary);
		
		EmploymentDao dao = new EmploymentImpl();
		boolean success = dao.insert(vo);
		
		System.out.println("연락처가 " + (success ? "추가되었습니다" : "추가되지않았습니다"));
	}
		
	
	// 메세지 전달
	private static void sendMessage(Long id) {
		Scanner scanner = new Scanner(System.in);
		MessageRecordDao dao = new MessageRecordImpl();
		
		System.out.println("전달할 메시지를 입력하세요.");
		String message = scanner.nextLine();
		boolean result = dao.send(message, id);
		System.out.println("메시지가 " + (result ? "전달되었습니다" : "전달되지 않았습니다"));
	}
	
	// 메세지 목록
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
	
	//메세지 얻기
	private static void getMessage(Long id) {
		MessageRecordDao dao = new MessageRecordImpl();
		MessageRecordVo vo = dao.getVo(id);
		System.out.printf("id : %d\n사원번호 : %d\t 이름 : %s\t 전화번호 : %s\t 송신시간 : %s\n메시지 : %s\n ",
				vo.getId(), vo.getEmployeeId(), vo.getEmployeeName(), vo.getEmployeeNumber(),
				vo.getRegDate(), vo.getMessageComment());
	}
	
	// 메세지 읽음 기능
	private static void updateMessageRead(Long id) {
		MessageRecordDao dao = new MessageRecordImpl();
		dao.readUpdate(id);
	}
	
   // 사원 삭제
	private static void deleteEmployment(Long id) {
		EmploymentDao dao = new EmploymentImpl();
		boolean success = dao.delete(id);
		
		System.out.println("연락처가 " + (success ? "삭제되었습니다" : "삭제되지않았습니다"));
	}
	
	// 사원 1명의 더 많은 정보
	private static void showDetail(Long keyword) {
		EmploymentDao dao = new EmploymentImpl();
		EmploymentVo vo = dao.getEmploymentById(keyword);
		System.out.printf("사원 번호 : %d\t 사원 이름 : %s\t 사원 직급 : %s\t 사원 나이 : %s\n"
				+ "사원 연봉 : %d\t 사원 전화번호 : %s\t 부서 번호 : %d\t 사원 이메일 : %s\n",
				vo.getId(), vo.getName(), vo.getEmployeeRank(), vo.getAge(), vo.getSalary(),
				 vo.getNumber(), vo.getDepartmentId(), vo.getEmail());
	}

	// 뒤로가기
	private static void back() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("0.뒤로가기");
			System.out.print(">");
			int back = scanner.nextInt(); 
			if (back == 0) {
				break;
			} else {
				System.out.println("잘못된 입력");
			}
		}
	}
	
	//	부서별 목록
	private static void listEmploymentByDepartment(Long dptId) {
		EmploymentDao dao = new EmploymentImpl();
		List<DepartmentListVo> list = dao.searchDptId(dptId);
			Iterator<DepartmentListVo> iter = list.iterator();
			while(iter.hasNext()) {
				DepartmentListVo vo = iter.next();
				System.out.printf("id : %d\t  부서 이름 : %s\t    이름 : %s\t   나이 : %s\t 전화번호 : %s\t   직급 : %s\n", vo.getId(), vo.getDepartmentName(),  vo.getName(), vo.getAge(), vo.getNumber(), vo.getEmployeeRank());
			}
	}
	
	//	지사별 목록
	private static void listEmploymentByRegion(Long keyRegions) {
		EmploymentDao dao = new EmploymentImpl();
		List<RegionsVo> list = dao.searchRegions(keyRegions);
			Iterator<RegionsVo> iter = list.iterator();
			while(iter.hasNext()) {
				RegionsVo vo = iter.next();
				System.out.printf("id : %d\t   지사 이름 : %s\t   이름 : %s\t  나이 : %s\t 전화번호 : %s\t    부서 이름 : %s\t 직급 : %s\n", 
						vo.getId(), vo.getRegionsName(), vo.getName(), vo.getAge(), vo.getNumber(), vo.getDepartmentName(), vo.getEmployeeRank());
			}
	}
	
	//	직급별 목록 
	public static void listEmploymentByRank(int id) {
		String a = "";
		switch(id) {
		case 1 : {
			a = "사원";
			break;
		}
		case 2 : {
			a = "대리";
			break;
		}
		case 3 : {
			a = "과장";
			break;
		}
		case 4 : {
			a = "차장";
			break;
		}
		case 5 : {
			a = "부장";
			break;
		}
		default : {
			System.out.print("error");
		}
		}
		System.out.println(a);
		EmploymentDao dao = new EmploymentImpl();
		List<RankVo> list = dao.searchRank(a);
			Iterator<RankVo> iter = list.iterator();
			while(iter.hasNext()) {
				RankVo vo = iter.next();
				System.out.printf("id : %d\t  직급 : %s\t 이름 : %s\t  나이 : %s\t 전화번호 : %s\t   부서이름 : %s\t    연봉 : %d\n", 
						vo.getId(), vo.getEmployeeRank(), vo.getName(), vo.getAge(), vo.getNumber(), vo.getDepartmentName(), vo.getSalary());
			}
	}
	
	private static void updateEmployment(int col, String value, Long id) {
		EmploymentDao dao = new EmploymentImpl();
		boolean flag = dao.updateCustom(col, value, id);
		if(flag == true) {
			System.out.println("성공");
		} else {
			System.out.println("실패");
		}
	}
}