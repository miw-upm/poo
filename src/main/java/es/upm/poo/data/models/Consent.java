package es.upm.poo.data.models;

import java.time.LocalDateTime;

public class Consent {
    private Long id;
    private  LocalDateTime grantedAt;
    private String source;
    private LocalDateTime revokedAt;
    private ConsentPurpose purpose;
    private User user;

}
