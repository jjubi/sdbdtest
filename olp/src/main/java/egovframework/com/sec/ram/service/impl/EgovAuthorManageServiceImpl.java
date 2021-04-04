package egovframework.com.sec.ram.service.impl;

import java.sql.SQLException;
import java.util.List;

import egovframework.com.sec.ram.service.AuthorHierarchyVO;
import egovframework.com.sec.ram.service.AuthorManage;
import egovframework.com.sec.ram.service.AuthorManageScrtyestbs;
import egovframework.com.sec.ram.service.AuthorManageVO;
import egovframework.com.sec.ram.service.EgovAuthorManageService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.vaiv.kr.cmmn.common.util.Loggable;

import javax.annotation.Resource;
import javax.ws.rs.ProcessingException;

import org.springframework.stereotype.Service;

/**
 * 권한관리에 관한 ServiceImpl 클래스를 정의한다.
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
 *   2009.03.11  이문준          최초 생성
 *
 * </pre>
 */

@Service("egovAuthorManageService")
public class EgovAuthorManageServiceImpl extends EgovAbstractServiceImpl implements EgovAuthorManageService {
    
	@Resource(name="authorManageDAO")
    private AuthorManageDAO authorManageDAO;

    /**
	 * 권한 목록을 조회한다.
	 * @param authorManageVO AuthorManageVO
	 * @return List<AuthorManageVO>
	 * @exception SQLException
	 */
    public List<AuthorManageVO> selectAuthorList(AuthorManageVO authorManageVO) throws SQLException {
        return authorManageDAO.selectAuthorList(authorManageVO);
    }
    
	/**
	 * 권한을 등록한다.
	 * @param authorManage AuthorManage
	 * @exception SQLException
	 */
    public void insertAuthor(AuthorManage authorManage) throws SQLException {
    	authorManageDAO.insertAuthor(authorManage);
    }

    /**
	 * 권한을 수정한다.
	 * @param authorManage AuthorManage
	 * @exception SQLException
	 */
    public void updateAuthor(AuthorManage authorManage) throws SQLException {
    	authorManageDAO.updateAuthor(authorManage);
    }

    /**
	 * 권한을 삭제한다.
	 * @param authorManage AuthorManage
	 * @exception SQLException
	 */
    public void deleteAuthor(AuthorManage authorManage) throws SQLException {
    	authorManageDAO.deleteAuthor(authorManage);
    }

    /**
	 * 권한을 조회한다.
	 * @param authorManageVO AuthorManageVO
	 * @return AuthorManageVO
	 * @exception SQLException
	 */
    public AuthorManageVO selectAuthor(AuthorManageVO authorManageVO) throws SQLException {
    	AuthorManageVO resultVO = authorManageDAO.selectAuthor(authorManageVO);
        if (resultVO == null) {
        	try {
        		throw processException("info.nodata.msg");
        	} catch (SQLException e) {
        		new Loggable().exLogging("selectAuthor", e);
			} catch (Exception e) {
				new Loggable().exLogging("selectAuthor", e);
			}
        }
        return resultVO;
    }

    /**
	 * 권한 목록 카운트를 조회한다.
	 * @param authorManageVO AuthorManageVO
	 * @return int
	 * @exception SQLException
	 */
    public int selectAuthorListTotCnt(AuthorManageVO authorManageVO) throws SQLException {
        return authorManageDAO.selectAuthorListTotCnt(authorManageVO);
    }    
    
    /**
	 * 모든 권한목록을 조회한다.
	 * @param authorManageVO AuthorManageVO
	 * @return List<AuthorManageVO>
	 * @exception SQLException
	 */
	public List<AuthorManageVO> selectAuthorAllList(AuthorManageVO authorManageVO) throws SQLException {
    	return authorManageDAO.selectAuthorAllList(authorManageVO);
    }
	/**
	 * 사용자 보안설정 저장
	 * */
	@Override
	public void insertUserScrtyestbs(AuthorManageScrtyestbs vo) throws SQLException {
		authorManageDAO.insertUserScrtyestbs(vo);
	}

	/**
	 * 사용자 보안설정 수정
	 * */
	@Override
	public void updateUserScrtyestbs(AuthorManageScrtyestbs vo) throws SQLException {
		authorManageDAO.updateUserScrtyestbs(vo);
	}

	@Override
	public int checkAuthorCode(AuthorManage vo) throws SQLException {
		return authorManageDAO.checkAuthorCode(vo);
	}

	@Override
	public List<AuthorHierarchyVO> selectAuthorHierarchyList() throws SQLException {
		return authorManageDAO.selectAuthorHierarchyList();
	}

	@Override
	public void insertAuthorHierarchy(AuthorHierarchyVO vo) throws SQLException {
		//삭제
		authorManageDAO.deleteAuthorHierarchy(vo);
		//저장
		String[] parntsRoles = vo.getParntsRole().split(",");
		String[] chldrnRoles = vo.getChldrnRole().split(",");
		for(int i = 0; i < parntsRoles.length; i++) {
			AuthorHierarchyVO insertVO = new AuthorHierarchyVO();
			insertVO.setParntsRole(parntsRoles[i]);
			insertVO.setChldrnRole(chldrnRoles[i]);
			authorManageDAO.insertAuthorHierarchy(insertVO);
		}
	}      
}
