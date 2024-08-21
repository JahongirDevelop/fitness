package uni.project.fitness.servise;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uni.project.fitness.dto.request.TeacherRequestDTO;
import uni.project.fitness.dto.response.TeacherResponseDTO;
import uni.project.fitness.dto.response.TrainingResponseDTO;
import uni.project.fitness.entity.Teacher;
import uni.project.fitness.entity.Training;
import uni.project.fitness.exception.DataNotFoundException;
import uni.project.fitness.repository.TeacherRepository;
import uni.project.fitness.repository.TrainingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
    public TeacherResponseDTO createTeacher(TeacherRequestDTO requestDTO) {
        Teacher teacher = Teacher.builder()
                .name(requestDTO.getName())
                .image(requestDTO.getImage())
                .workType(requestDTO.getWorkType())
                .about(requestDTO.getAbout())
                .build();
        teacher = teacherRepository.save(teacher);
        return convertToResponseDTO(teacher);
    }

    public TeacherResponseDTO updateTeacher(UUID id, TeacherRequestDTO requestDTO) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Teacher not found"));
        teacher.setName(requestDTO.getName());
        teacher.setImage(requestDTO.getImage());
        teacher.setWorkType(requestDTO.getWorkType());
        teacher.setAbout(requestDTO.getAbout());
        teacher = teacherRepository.save(teacher);
        return convertToResponseDTO(teacher);
    }

    public void deleteTeacher(UUID id) {
        teacherRepository.deleteById(id);
    }

    public TeacherResponseDTO getTeacherById(UUID id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Teacher not found"));
        return convertToResponseDTO(teacher);
    }

    public List<TeacherResponseDTO> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private TeacherResponseDTO convertToResponseDTO(Teacher teacher) {
        return TeacherResponseDTO.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .image(teacher.getImage())
                .workType(teacher.getWorkType())
                .about(teacher.getAbout())
                .trainings(teacher.getTrainingList() != null
                        ? teacher.getTrainingList().stream()
                        .map(Training::getId)
                        .collect(Collectors.toList())
                        : new ArrayList<>()) // Handle case where trainings might be null
                .build();
    }

    private TrainingResponseDTO convertTrainingToResponseDTO(Training training) {
        return TrainingResponseDTO.builder()
                .id(training.getId())
                .title(training.getTitle())
                .teacher(convertTeacherToResponseDTO(training.getTeacher()))
                .description(training.getDescription())
                .shortDescription(training.getShortDescription())
                .image(training.getImage())
                .video(training.getVideo())
                .importantInfo(training.getImportantInfo())
                .equipments(training.getEquipments())
                .musclesInvolved(training.getMusclesInvolved())
                .results(training.getResults())
                .build();
    }

    private TeacherResponseDTO convertTeacherToResponseDTO(Teacher teacher) {
        return TeacherResponseDTO.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .image(teacher.getImage())
                .workType(teacher.getWorkType())
                .about(teacher.getAbout())
                .build();
    }
}
