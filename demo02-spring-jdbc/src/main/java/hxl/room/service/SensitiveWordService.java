package hxl.room.service;

import org.springframework.stereotype.Service;

import java.util.List;

public interface SensitiveWordService {

    List<String> getWords(Long companyId);

    void saveWords(Long companyId, String words);
}
