package com.plusplm.controllers.api.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.plusplm.SystemInfor;
import com.plusplm.config.Globals;
import com.plusplm.exception.ErrorCode;
import com.plusplm.exception.ProcessingException;
import com.plusplm.model.Code;
import com.plusplm.repository.CodeMongoRepository;

/**
 * 코드
 */
@RestController
@RequestMapping("/api/v1")
public class CodeRestController {

	private CodeMongoRepository codeMongoRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	public CodeRestController(CodeMongoRepository codeMongoRepository) {
		this.codeMongoRepository = codeMongoRepository;
	}

	/**
	 * 페이지로딩
	 */
	@RequestMapping(value = "/code", method = RequestMethod.GET)
	public Page<Code> fineAllCode(Pageable pageable) throws Exception{
		Query Query = new Query().with(pageable).with(new Sort(Sort.Direction.ASC, "order"));
		Query.addCriteria(new Criteria("delYN").all("N"));
		List<Code> list = mongoTemplate.find(Query, Code.class, "Code");
		return PageableExecutionUtils.getPage(list, pageable, () -> mongoTemplate.count(Query, Code.class, "Code"));
	}

	/**
	 * 상세조회
	 */
	@RequestMapping(value = "/code/{id}", method = RequestMethod.GET)
	public @ResponseBody Code findCodeById(@PathVariable String id) throws Exception{
		return codeMongoRepository.findOne(id);
	}

	/**
	 * 코드 중복체크
	 */
	@RequestMapping(value = "/dupl/code", method = RequestMethod.GET)
	@ResponseBody
	public long duplCode(@RequestParam(value = "codeGroup", required = false) String codeGroup
			, @RequestParam(value = "codeId", required = false) String codeId) throws Exception {
		Query Query = new Query();
		Query.addCriteria(new Criteria("delYN").all("N"));
		Query.addCriteria(new Criteria("codeGroup").all(codeGroup));
		Query.addCriteria(new Criteria("codeId").all(codeId));
		long duplCnt = mongoTemplate.count(Query, Code.class, "Code");
		return duplCnt;
	}

	/**
	 * 등록
	 */
	@RequestMapping(value = "/code", method = RequestMethod.POST)
	public Code addCode(@RequestBody Code codeRequest) throws Exception{
		try {
			codeRequest.setRegDate(SystemInfor.SysDate());
			codeRequest.setRegIp(SystemInfor.getUserIp());
			codeRequest.setRegId(Globals.USER_ID);
			codeRequest.setRegName(Globals.USER_NAME);
			codeRequest.setDelYN("N");
			
			codeMongoRepository.save(codeRequest);
		} catch (Exception e) {
			throw new ProcessingException(e.getMessage(), ErrorCode.UNKNOWN_ERROR);
		}
		return codeRequest;
	}

	/**
	 * 수정
	 */
	@RequestMapping(value = "/code/{id}", method = RequestMethod.PUT)
	public @ResponseBody Code updateCode(@PathVariable String id, @RequestBody Code codeRequest) throws Exception{
		Code code = codeMongoRepository.findOne(id);
		try {
			if (!"".equals(SystemInfor.isNull(codeRequest.getMasterId()))) {
				code.setMasterId(codeRequest.getMasterId());
			}
			if (!"".equals(SystemInfor.isNull(codeRequest.getCodeId()))) {
				code.setCodeId(codeRequest.getCodeId());
			}
			if (!"".equals(SystemInfor.isNull(codeRequest.getCodeName()))) {
				code.setCodeName(codeRequest.getCodeName());
			}
			if (!"".equals(SystemInfor.isNull(codeRequest.getCodeGroup()))) {
				code.setCodeGroup(codeRequest.getCodeGroup());
			}
			if (!"".equals(SystemInfor.isNull(codeRequest.getOrder()))) {
				code.setOrder(codeRequest.getOrder());
			}
			if (!"".equals(SystemInfor.isNull(codeRequest.getCodeNote()))) {
				code.setCodeNote(codeRequest.getCodeNote());
			}
			if (!"".equals(SystemInfor.isNull(codeRequest.getDelYN()))) {
				code.setDelYN(codeRequest.getDelYN());
			}
			if (!"".equals(SystemInfor.isNull(codeRequest.getUseYN()))) {
				code.setUseYN(codeRequest.getUseYN());
			}
			code.setModDate(SystemInfor.SysDate());
			code.setModIp(SystemInfor.getUserIp());
			code.setModId(Globals.USER_ID);
			code.setModName(Globals.USER_NAME);
			codeMongoRepository.save(code);
		} catch (Exception e) {
			throw new ProcessingException(e.getMessage(), ErrorCode.UNKNOWN_ERROR);
		}
		return code;
	}

	/**
	 * 삭제
	 */
	@RequestMapping(value = "/code/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Code deleteCode(@PathVariable String id) throws Exception{
		Code code = codeMongoRepository.findOne(id);
		try {
			if(code != null) {
				code.setDelYN("Y");
				codeMongoRepository.save(code);
			}
		} catch (Exception e) {
			throw new ProcessingException(e.getMessage(), ErrorCode.UNKNOWN_ERROR);
		}
		return code;
	}

	/**
	 * 조회
	 */
	@RequestMapping(value = "/search/code", method = RequestMethod.GET)
	@ResponseBody
	public Page<Code> searchcode(@RequestParam(value = "codeName", required = false) String codeName,
			@RequestParam(value = "codeGroup", required = false) String codeGroup,
			@RequestParam(value = "masterId", required = false) String masterId,
			@RequestParam(value = "sortOrder", required = false) String sortOrder,
			@RequestParam(value = "sortName", required = false) String sortName, Pageable pageable) throws Exception {
		
		Query Query = SystemInfor.getOrderSort(pageable, sortName, sortOrder);
		Query.addCriteria(new Criteria("delYN").all("N"));
		
		if (!"".equals(SystemInfor.isNull(codeName))) {
			Query.addCriteria(new Criteria("codeName").all(codeName));
		}
		if (!"".equals(SystemInfor.isNull(codeGroup))) {
			Query.addCriteria(new Criteria("codeGroup").all(codeGroup));
		}
		if (!"".equals(SystemInfor.isNull(masterId))) {
			Query.addCriteria(new Criteria("masterId").all(masterId));
		}

		List<Code> list = mongoTemplate.find(Query, Code.class, "Code");
		return PageableExecutionUtils.getPage(list, pageable, () -> mongoTemplate.count(Query, Code.class, "Code"));
	}
}
