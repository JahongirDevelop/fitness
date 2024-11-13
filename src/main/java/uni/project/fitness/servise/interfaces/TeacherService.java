package uni.project.fitness.servise.interfaces;

import uni.project.fitness.dto.request.*;
import uni.project.fitness.dto.response.*;
import java.util.List;
import java.util.UUID;
public interface TeacherService {
    TeacherResponseDTO createTeacher(TeacherRequestDTO requestDTO);
    TeacherResponseDTO updateTeacher(UUID id, TeacherRequestDTO requestDTO);
    void deleteTeacher(UUID id);
    TeacherResponseDTO getTeacherById(UUID id);
    List<TeacherResponseDTO> getAllTeachers();
}

