package HookKiller.server.common.controller;


import HookKiller.server.common.dto.ImageUploadRequest;
import HookKiller.server.common.file.NaverObjectStorageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/file")
@RequiredArgsConstructor
@RestController
public class FileController {

    private final NaverObjectStorageUtil naverObjectStorageUtil;

    @PostMapping("/image")
    public ResponseEntity<List<String>> uploadImages(ImageUploadRequest request) {
        // TODO : 사용자 정보 기록위한 시큐리티 유틸 제작이후 추가진행필요
        return null;
    }
}
