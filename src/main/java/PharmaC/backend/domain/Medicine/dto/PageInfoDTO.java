package PharmaC.backend.domain.Medicine.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageInfoDTO {
    private int pageNum;
    private int limit;
    private int totalPages;
    private int totalElements;

}
