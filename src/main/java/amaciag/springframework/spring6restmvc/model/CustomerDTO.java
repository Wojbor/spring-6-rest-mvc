package amaciag.springframework.spring6restmvc.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@Slf4j
public class CustomerDTO {
    private UUID id;
    private String customerName;
    private Integer version;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifyDate;
}
