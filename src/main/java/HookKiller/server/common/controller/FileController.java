package HookKiller.server.common.controller;


import HookKiller.server.common.dto.FileUploadResponse;
import HookKiller.server.common.dto.ImageUploadRequest;
import HookKiller.server.common.dto.ImagesUploadRequest;
import HookKiller.server.common.service.FileService;
import jakarta.validation.Valid;
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

    private final FileService fileService;

    /**
     * 복수개의 이미지를 업로드시 사용한다
     * @param request
     * @return
     */
    @PostMapping("/images")
    public ResponseEntity<List<FileUploadResponse>> uploadImages(@Valid ImagesUploadRequest request) {
        return ResponseEntity.ok(fileService.getUploadImagePaths(request));
    }

    /**
     * 단수개의 이미지를 업로드시 사용한다
     * @param request
     * @return
     */
    @PostMapping("/image")
    public ResponseEntity<FileUploadResponse> uploadImages(@Valid ImageUploadRequest request) {
        return ResponseEntity.ok(fileService.getUploadImagePaths(request));
    }
}
