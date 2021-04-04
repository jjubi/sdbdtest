package egovframework.com.sec.ram.service;

import java.sql.SQLException;
import java.util.List;

/**
 * 권한관리에 관한 서비스 인터페이스 클래스를 정의한다.
 * @author 공통서비스 개발팀 이문준
 * @since 2009.06.01
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *   
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.03.20  이문준          최초 생성
 *
 * </pre>
 */

public interface EgovAuthorManageService {
    /**
	 * 모든 권한목록을 조회한다.
	 * @param authorManageVO AuthorManageVO
	 * @return List<AuthorManageVO>
	 * @exception SQLException
	 */
	public List<AuthorManageVO> selectAuthorAllList(AuthorManageVO authorManageVO) throws SQLException;
	
	/**
	 * 시스템 사용자중 불필요한 시스템권한정보를 화면에 조회하여 데이터베이스에서 삭제
	 * @param authorManage AuthorManage
	 * @exception SQLException
	 */
	public void deleteAuthor(AuthorManage authorManage) throws SQLException;

	/**
	 * 사용자의 시스테접근권한를 화면에서 입력하여 입력항목의 정합성을 체크하고 데이터베이스에 저장
	 * @param authorManage AuthorManage
	 * @exception SQLException
	 */
	public void insertAuthor(AuthorManage authorManage) throws SQLException;

	/**
	 * 개별사용자에게 할당된 권한 조회
	 * @param authorManageVO AuthorManageVO
	 * @exception SQLException
	 */
	public AuthorManageVO selectAuthor(AuthorManageVO authorManageVO) throws SQLException;

	/**
	 * 개별사용자에게 할당된 권한리스트 조회
	 * @param authorManageVO AuthorManageVO
	 * @return List<AuthorManageVO>
	 * @exception SQLException
	 */
	public List<AuthorManageVO> selectAuthorList(AuthorManageVO authorManageVO) throws SQLException;

	/**
	 * 화면에 조회된 사용자권한정보를 수정하여 항목의 정합성을 체크하고 수정된 데이터를 데이터베이스에 반영
 	 * @param authorManage AuthorManage
	 * @exception SQLException
	 */
	public void updateAuthor(AuthorManage authorManage) throws SQLException;
	
    /**
	 * 목록조회 카운트를 반환한다
	 * @param authorManageVO AuthorManageVO
	 * @return int
	 * @exception SQLSQLException
	 */
	public int selectAuthorListTotCnt(AuthorManageVO authorManageVO) throws SQLException;
	
	/**
	 * 사용자 보안설정 저장
	 * */
	public void insertUserScrtyestbs(AuthorManageScrtyestbs vo) throws SQLException;
	
	/**
	 * 사용자 보안설정 수정
	 * */
	public void updateUserScrtyestbs(AuthorManageScrtyestbs vo) throws SQLException;
	/**
	 * 권한 코드 중복 체크
	 * */
	public int checkAuthorCode(AuthorManage vo) throws SQLException;
	/**
	 * 권한 계층 구조 목록 가져오기
	 * */
	public List<AuthorHierarchyVO> selectAuthorHierarchyList() throws SQLException;
	
	/**
	 * 권한 계층 구조 저장
	 * */
	public void insertAuthorHierarchy(AuthorHierarchyVO vo) throws SQLException;
	
	
}
