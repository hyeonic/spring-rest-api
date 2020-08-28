package com.hyeonic.springrestapi.events;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@EqualsAndHashCode(of = {"id", "name"}) // 다른 Entity와 엮이는 것은 좋지 않다. => 스택오버플로우 발생가능
@Builder @NoArgsConstructor
@AllArgsConstructor
public class Event {

    private Integer id;
    private String name;
    private String description;
    private LocalDateTime beginEnrollmentDateTime;
    private LocalDateTime closeEnrollmentDateTime;
    private LocalDateTime beginEventDateTime;
    private LocalDateTime endEventDateTime;
    private String location; // (optional) 이게 없으면 온라인 모임
    private int basePrice; // (optional)
    private int maxPrice; // (optional)
    private int limitOfEnrollment;
    private boolean offline;
    private boolean free;
    private EventStatus eventStatus = EventStatus.DRAFT;

}
