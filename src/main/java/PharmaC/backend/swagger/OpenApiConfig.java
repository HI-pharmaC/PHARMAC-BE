package PharmaC.backend.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info().title("PHARMA-C API")
                .description("졸업 프로젝트 파마씨의 API docs 입니다.");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
