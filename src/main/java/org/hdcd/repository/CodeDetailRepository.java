package org.hdcd.repository;

import java.util.List;

import org.hdcd.domain.CodeDetail;
import org.hdcd.domain.CodeDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CodeDetailRepository extends JpaRepository<CodeDetail, CodeDetailId> {

	@Query("SELECT max(cd.sortSeq) FROM CodeDetail cd WHERE cd.groupCode = ?1")
	public List<Object[]> getMaxSortSeq(String groupCode);
	
}
