package com.song.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.song.model.TestTbVO;

@Repository
public interface TestRepository extends JpaRepository<TestTbVO, Long> {

	List<TestTbVO> findByTestCol1(@Param("testCol1") Integer testCol1);

}
