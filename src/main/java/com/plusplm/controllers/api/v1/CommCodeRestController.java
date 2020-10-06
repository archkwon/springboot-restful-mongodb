package com.plusplm.controllers.api.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.plusplm.model.CommAuth;
import com.plusplm.model.CommBlock;
import com.plusplm.model.CommCode;
import com.plusplm.model.CommDept;
import com.plusplm.model.CommDwgClass;
import com.plusplm.model.CommGroup;
import com.plusplm.model.CommShip;
import com.plusplm.model.CommUser;
import com.plusplm.model.CommWBS;
import com.plusplm.model.CommWBSDeta;
import com.plusplm.model.CommZone;

/**
 * 코드
 */
@RestController
@RequestMapping("/api/v1")
public class CommCodeRestController {

	@Autowired
	MongoTemplate mongoTemplate;

	@RequestMapping(value = "/comm/{gbn}", method = RequestMethod.GET)
	@ResponseBody
	public List<?> searchCmm(@PathVariable String gbn, @RequestParam(value = "master", required = false) String master,
			@RequestParam(value = "codeId", required = false) String codeId,
			@RequestParam(value = "wbsId", required = false) String wbsId,
			@RequestParam(value = "name", required = false) String name, Pageable pageable) {

		List<?> list = null;
		Query Query = new Query();

		// 호선
		if (gbn.equals("Ship")) {
			list = mongoTemplate.find(Query, CommShip.class, gbn);
		}
		// 부서
		if (gbn.equals("Dept")) {
			Query = new Query();
			Query.with(new Sort(Sort.Direction.ASC, "deptName"));
			list = mongoTemplate.find(Query, CommDept.class, gbn);
		}
		// 그룹
		if (gbn.equals("Group")) {
			Query = new Query();
			Query.with(new Sort(Sort.Direction.ASC, "order"));
			list = mongoTemplate.find(Query, CommGroup.class, gbn);
		}
		
		// 적용권한정보
		if (gbn.equals("Auth")) {
			Query = new Query();
			Query.with(new Sort(Sort.Direction.ASC, "authName"));
			list = mongoTemplate.find(Query, CommAuth.class, gbn);
		}
		
		// 사용자
		if (gbn.equals("User")) {
			Query.addCriteria(new Criteria("deptId").all(codeId));
			Query.addCriteria(new Criteria("delYN").all("N"));
			list = mongoTemplate.find(Query, CommUser.class, gbn);
		}
		// 존
		if (gbn.equals("Zone")) {
			Query.addCriteria(new Criteria("delYN").all("N"));
			list = mongoTemplate.find(Query, CommZone.class, gbn);
		}
		// 블럭
		if (gbn.equals("Block")) {
			Query.addCriteria(new Criteria("delYN").all("N"));
			list = mongoTemplate.find(Query, CommBlock.class, gbn);
		}
		// 공통코드
		if (gbn.equals("Code")) {
			// master
			Query = new Query().with(new Sort(Sort.Direction.ASC, "order"));

			Query.addCriteria(new Criteria("codeGroup").all(master));
			Query.addCriteria(new Criteria("delYN").all("N"));
			Query.addCriteria(new Criteria("useYN").all("Y"));
			list = mongoTemplate.find(Query, CommCode.class, gbn);
		}

		// 표준도면분류
		if (gbn.equals("DwgClass")) {
			Query.addCriteria(new Criteria("delYN").all("N"));
			list = mongoTemplate.find(Query, CommDwgClass.class, gbn);

		}

		// 마스터스케줄
		if (gbn.equals("WBS")) {
			Query.addCriteria(new Criteria("delYN").all("N"));
			list = mongoTemplate.find(Query, CommWBS.class, gbn);

		}

		// 마스터스케줄 상세
		if (gbn.equals("WBSDeta")) {
			if (wbsId != null && wbsId != "") {
				Query.addCriteria(new Criteria("wbsId").all(wbsId));
			}
			if (master != null && master != "") {
				Query.addCriteria(new Criteria("wbsCode").all(master));
			}

			Query.addCriteria(new Criteria("delYN").all("N"));
			list = mongoTemplate.find(Query, CommWBSDeta.class, gbn);
		}
		return list;
	}
}
