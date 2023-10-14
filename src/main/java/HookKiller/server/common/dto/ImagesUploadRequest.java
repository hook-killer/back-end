package HookKiller.server.common.dto;

import HookKiller.server.common.file.NaverObjectStorageUsageType;
import HookKiller.server.common.util.valid.ValidEnum;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class ImagesUploadRequest {
    //@ValidEnum(enumClass = NaverObjectStorageUsageType.class, message = "사용처를 입력해 주시기 바랍니다.")
    @NotNull(message = "사용처를 입력해 주시기 바랍니다.")
    private NaverObjectStorageUsageType naverObjectStorageUsageType;

    @NotNull(message = "업로드한 파일이 존재하지 않습니다.")
    private List<MultipartFile> images;
}
