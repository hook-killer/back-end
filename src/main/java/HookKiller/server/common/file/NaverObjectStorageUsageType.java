package HookKiller.server.common.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NaverObjectStorageUsageType {
    PROFILE("profile"),
    BOARD("board"),
    ADMIN("admin"),
    ARTICLE("article");


    private String path;
}
