package hxl.room.controller;

import com.hxl.resultUtil.NoResultResponse;
import com.hxl.resultUtil.ResultResponse;
import com.hxl.room.domain.dto.SensitiveWordSaveDTO;
import com.hxl.room.repository.SensitiveWordRepository;
import com.hxl.room.service.SensitiveWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private SensitiveWordService sensitiveWordservice;
    @Autowired
    private SensitiveWordRepository sensitiveWordRepository;

    /**
     * 查询配置
     */
    @GetMapping("/sensitive")
    public ResultResponse<List<String>> getSensitiveWords(@RequestParam Long companyId) {

        List<String> result = sensitiveWordservice.getWords(companyId);

        return ResultResponse.ok(result);
    }

    @GetMapping("/sensitive-word")
    public ResultResponse<String> getSensitiveWordsInfo(@RequestParam Long companyId) {
        String result = sensitiveWordRepository.getSensitiveWords(companyId);
        result = Optional.ofNullable(result).orElse("");

        return ResultResponse.ok(result);
    }

    /**
     * 保存配置
     */
    @PostMapping("/sensitive-word")
    public NoResultResponse savaSensitiveWords(@RequestParam Long companyId,
                                               @RequestBody SensitiveWordSaveDTO sensitiveWordSaveDTO) {
        sensitiveWordservice.saveWords(companyId, sensitiveWordSaveDTO.getWords());

        return NoResultResponse.INSTANCE;
    }
}
