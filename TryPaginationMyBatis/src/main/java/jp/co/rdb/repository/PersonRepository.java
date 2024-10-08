package jp.co.rdb.repository;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import jp.co.rdb.entity.Person;

// Mapper Interface
// @MapperScan を書かない場合は、Mapper Repository のクラスに @Mapper アノテーションを付ける必要がある。
// @Mapper
public interface PersonRepository {

	public List<Person> selectAll(RowBounds rowBounds);

	public Long selectAllCount();

	public List<Person> findByShimei(RowBounds rowBounds, String shimei);

	public Long findByShimeiCount(String shimei);

	public List<Person> findByShimeiLike(RowBounds rowBounds, String shimei);

	public Long findByShimeiLikeCount(String shimei);

	public int deleteById(Integer id);

	int insert(String shimei);

}
