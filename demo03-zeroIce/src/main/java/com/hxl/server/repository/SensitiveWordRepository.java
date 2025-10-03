package com.hxl.server.repository;

import com.hxl.server.domain.entity.SensitiveWord;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * 企业敏感词配置接口
 *
 * @author hengxiaoliang
 */

public interface SensitiveWordRepository extends CrudRepository<SensitiveWord, Long> {

    /**
     * 根据企业ID查询敏感词
     * @param companyId 企业ID
     * @return 敏感词
     */
    @Query("select words from t_sensitive_word where company_id = :companyId")
    String getSensitiveWords(@Param("companyId") Long companyId);

    @Query("select * from t_sensitive_word where company_id = :companyId")
    SensitiveWord getSensitiveWordsInfo(Long companyId);
}
