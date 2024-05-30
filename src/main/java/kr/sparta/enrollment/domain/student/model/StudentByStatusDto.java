package kr.sparta.enrollment.domain.student.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentByStatusDto {
    private Long id;
    private String name;
}
