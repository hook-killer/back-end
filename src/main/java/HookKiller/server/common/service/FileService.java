package HookKiller.server.common.service;

import HookKiller.server.common.dto.FileUploadResponse;
import HookKiller.server.common.dto.ImageUploadRequest;
import HookKiller.server.common.dto.ImagesUploadRequest;
import HookKiller.server.common.entity.FileResources;
import HookKiller.server.common.file.NaverObjectStorageUsageType;
import HookKiller.server.common.file.NaverObjectStorageUtil;
import HookKiller.server.common.repository.FileRepository;
import HookKiller.server.common.util.UserUtils;
import HookKiller.server.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {
    private final UserUtils userUtils;
    private final NaverObjectStorageUtil naverObjectStorageUtil;
    private final FileRepository fileRepository;

    @Transactional
    public List<FileUploadResponse> getUploadImagePaths(ImagesUploadRequest request) {
        User loginUser = userUtils.getUser();
        NaverObjectStorageUsageType usageType = request.getNaverObjectStorageUsageType();
        return request.getImages().stream().map(
                images -> FileUploadResponse.of(
                        fileRepository.save(
                                FileResources.builder()
                                        .usageType(usageType)
                                        .path(naverObjectStorageUtil.storageFileUpload(usageType, images))
                                        .createdUser(loginUser)
                                        .build()
                        )
                )
        ).toList();
    }

    @Transactional
    public FileUploadResponse getUploadImagePaths(ImageUploadRequest request) {
        User loginUser = userUtils.getUser();
        NaverObjectStorageUsageType usageType = request.getNaverObjectStorageUsageType();
        return FileUploadResponse.of(
                fileRepository.save(
                        FileResources.builder()
                                .usageType(usageType)
                                .path(naverObjectStorageUtil.storageFileUpload(usageType, request.getImage()))
                                .createdUser(loginUser)
                                .build()
                )
        );
    }
}
