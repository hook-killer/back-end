package HookKiller.server.common.controller;


import HookKiller.server.common.dto.ImageUploadRequest;
import HookKiller.server.common.entity.FileResources;
import HookKiller.server.common.service.FileService;
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

    @PostMapping("/image")
    public ResponseEntity<List<FileResources>> uploadImages(ImageUploadRequest request) {
        return ResponseEntity.ok(fileService.getUploadImagePaths(request));
    }
}
