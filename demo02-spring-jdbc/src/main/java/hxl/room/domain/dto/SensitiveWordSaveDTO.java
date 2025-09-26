package hxl.room.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class SensitiveWordSaveDTO implements Serializable {

    private static final long serialVersionUID = 6546389221810037408L;

    private String words;
}
