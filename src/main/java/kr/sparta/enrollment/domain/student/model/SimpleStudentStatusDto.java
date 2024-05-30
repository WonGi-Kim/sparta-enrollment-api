package kr.sparta.enrollment.domain.student.model;

import jakarta.annotation.Nullable;
import lombok.Getter;

@Getter
public class SimpleStudentStatusDto {
    @Nullable
    private StudentStatus status;
}
