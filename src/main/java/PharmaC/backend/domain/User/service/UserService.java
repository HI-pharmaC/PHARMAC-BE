package PharmaC.backend.domain.User.service;

import PharmaC.backend.global.common.dto.AwsS3Url;
import PharmaC.backend.infra.s3.AwsS3UrlHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final AwsS3UrlHandler awsS3UrlHandler;

    @Transactional(readOnly = true)
    public AwsS3Url getImageUrl() {
        return awsS3UrlHandler.handle("user");
    }
}
