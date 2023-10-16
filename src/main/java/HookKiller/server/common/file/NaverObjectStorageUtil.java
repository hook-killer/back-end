package HookKiller.server.common.file;

import HookKiller.server.common.exception.FileDeleteException;
import HookKiller.server.common.exception.FileIOException;
import HookKiller.server.common.exception.FileUploadException;
import HookKiller.server.properties.NaverObjectStorageProperties;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static java.util.UUID.randomUUID;


@Slf4j
@RequiredArgsConstructor
@Component
public class NaverObjectStorageUtil {
    private final AmazonS3 storageObject;
    private final NaverObjectStorageProperties naverObjectStorageProperties;
    private final Environment environment;

    /**
     * 사용처, MultipartFile만 제공하면 업로드가 가능하다.
     *
     * @param usageType
     * @param file
     * @return
     * @throws FileUploadException
     */
    public String storageFileUpload(NaverObjectStorageUsageType usageType, MultipartFile file){
        try {
            String filePath = getPath(usageType, getFileUUIDNameByMultipartFile(file));

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getBytes().length);

            uploadFile(
                    usageType, filePath, file.getInputStream(), metadata
            );
            log.info("File Upload : {}", filePath);
            return filePath;
        } catch (IOException e) {
            throw FileIOException.EXCEPTION;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw FileUploadException.EXCEPTION;
        }
    }

    /**
     * 파일 삭제를 진행할 경우 사용한다
     *
     * @param filePath
     * @throws FileDeleteException
     */
    public void storageFileDelete(String filePath) throws FileDeleteException {
        try {
            storageObject.deleteObject(
                    naverObjectStorageProperties.getBucketName(),
                    filePath
            );
            log.info("File Delete : {}", filePath);
        } catch (Exception e) {
            throw FileDeleteException.EXCEPTION;
        }
    }


    /**
     * 실제 File을 NaverObject Storage에 Upload
     * Upload의 경우에는 결과값이 중요하지 않기 때문에 비동기 처리한다.
     *
     * @param usageType
     * @param filePath
     * @param uploadTarget
     * @param metadata
     */
    @Async
    public void uploadFile(NaverObjectStorageUsageType usageType, String filePath, InputStream uploadTarget, ObjectMetadata metadata) {
        storageObject.putObject(
                new PutObjectRequest(
                        naverObjectStorageProperties.getBucketName(),
                        filePath,
                        uploadTarget,
                        metadata
                ).withCannedAcl(CannedAccessControlList.PublicRead)
        );
    }

    /**
     * UUID.png 등으로 제작하기 위함
     *
     * @param file
     * @return
     */
    private String getFileUUIDNameByMultipartFile(MultipartFile file) {
        return String.join(".", randomUUID().toString(), FilenameUtils.getExtension(file.getOriginalFilename()));
    }

    /**
     * ObjectStorage에 저장될 File Path
     * Example) active : "dev", userType : "article", fileName : "a.jpg", 23year10Month9Day
     * "dev/article/2023/10/09/a.jpg"
     *
     * @param usageType
     * @param fileName
     * @return
     */
    private String getPath(NaverObjectStorageUsageType usageType, String fileName) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        return String.join("/",
                environment.getProperty("spring.profiles.active", "default"),
                usageType.getPath() ,
                String.valueOf(now.getYear()),
                String.valueOf(now.getMonthValue()),
                String.valueOf(now.getDayOfMonth()),
                fileName
        );
    }


}
