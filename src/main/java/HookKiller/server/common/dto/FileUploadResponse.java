package HookKiller.server.common.dto;

import HookKiller.server.common.entity.FileResources;
import HookKiller.server.common.file.NaverObjectStorageUsageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 어느 CDN에서 불러올지를 모르기 때문에 CDN링크는 `Front`에서 조절필요함.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
public class FileUploadResponse {
    private String filePath;
    private NaverObjectStorageUsageType usageType;

    public static FileUploadResponse of(FileResources fileResources) {
        return FileUploadResponse.builder()
                .filePath(fileResources.getPath())
                .usageType(fileResources.getUsageType())
                .build();
    }
}
