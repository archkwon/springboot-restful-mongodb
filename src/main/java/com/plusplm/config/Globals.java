package com.plusplm.config; 

public class Globals {

	public static final String USER_ID = "admin";
	public static final String USER_NAME = "관리자";
	public static final String SORT_DESC = "DESC";
	public static final String SORT_ASC = "ASC";
	
	/** 검증유형 */
	public static final String VERI_GBN_AUTO = "자동";
	public static final String VERI_GBN_NON_AUTO = "수동";
	
	/** M_99998 검증진행상황 */
	public static final String PROC_SITUA_CODE_W = "W";
	public static final String PROC_SITUA_CODE_A = "R";
	public static final String PROC_SITUA_CODE_C = "C";
	public static final String PROC_SITUA_CODE_E = "A";
	public static final String PROC_SITUA_CODE_Q = "E";
	public static final String PROC_SITUA_NAME_W = "대기";
	public static final String PROC_SITUA_NAME_A = "다시대기";
	public static final String PROC_SITUA_NAME_C = "실행완료";
	public static final String PROC_SITUA_NAME_E = "에러";
	public static final String PROC_SITUA_NAME_Q = "종료";
	
	/** TASK현황 구분값 */
	public static final String TASK_CODE_IS = "IS"; //이슈
	public static final String TASK_CODE_TO = "TO"; //TODO
	public static final String TASK_CODE_DP = "DP"; //도면실적
	public static final String TASK_CODE_VE = "VE"; //설계검증
	public static final String TASK_CODE_AI = "AI"; //AI
	
	/** 이슈, TODO 상태값*/
	public static final String TASK_GANBAN_CODE_S = "S";
	public static final String TASK_GANBAN_NAME_S = "진행";
	public static final String TASK_GANBAN_CODE_E = "E";
	public static final String TASK_GANBAN_NAME_E = "종료";
	
	/** M_99997 검증구분 */
	public static final String VERI_GBN_CODE_A = "A";
	public static final String VERI_GBN_CODE_B = "B";
	public static final String VERI_GBN_CODE_C = "C";
	public static final String VERI_GBN_CODE_D = "D";
	public static final String VERI_GBN_NAME_A = "자동검증";
	public static final String VERI_GBN_NAME_B = "수동검증";
	public static final String VERI_GBN_NAME_C = "AI자동검증";
	public static final String VERI_GBN_NAME_D = "AI수동검증";
	
	/** 단위프로그램 카테고리 */
	public static final String UNIT_CATE_CODE_PS = "PS"; //Block별간섭체크, Pipe스탠다드체크
	public static final String UNIT_CATE_CODE_CL = "CL"; //정적동적
	public static final String UNIT_CATE_CODE_AI = "AI"; //AI설계오류예측
	public static final String UNIT_CATE_CODE_AA = "AA"; //AI선주코멘트추천, AI유사선탐색
	public static final String UNIT_CATE_CODE_CA = "CA"; //3D모델데이터추출
	
}
