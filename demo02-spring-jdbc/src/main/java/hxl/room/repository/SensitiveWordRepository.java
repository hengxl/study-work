package hxl.room.repository;

import com.hxl.room.domain.entity.SensitiveWord;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SensitiveWordRepository extends CrudRepository<SensitiveWord, Long> {

    /**
     * 根据企业ID查询敏感词
     * @param companyId 企业ID
     * @return 敏感词
     */
    @Query("select words from t_sensitive_word where company_id = :companyId")
    String getSensitiveWords(@Param("companyId") Long companyId);

    /**
     * 根据企业ID查询配置信息
     * @param companyId 企业ID
     * @return 配置信息
     */
    @Query("select * from t_sensitive_word where company_id = :companyId")
    SensitiveWord getSensitiveWordsInfo(@Param("companyId") Long companyId);
}
