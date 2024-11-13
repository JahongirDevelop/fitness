package uni.project.fitness.servise.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.project.fitness.dto.request.TeacherRequestDTO;
import uni.project.fitness.dto.response.TeacherResponseDTO;
import uni.project.fitness.entity.Teacher;
import uni.project.fitness.exception.DataNotFoundException;
import uni.project.fitness.config.mapper.MyConverter;
import uni.project.fitness.repository.TeacherRepository;
import uni.project.fitness.servise.interfaces.TeacherService;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final MyConverter converter;

    @Override
    public TeacherResponseDTO createTeacher(TeacherRequestDTO requestDTO) {
        Teacher teacher = Teacher.builder()
                .name(requestDTO.getName())
                .image(requestDTO.getImage())
                .workType(requestDTO.getWorkType())
                .about(requestDTO.getAbout())
                .build();

        teacher = teacherRepository.save(teacher);
        return converter.convertToResponseDTO(teacher);
    }

    @Override
    public TeacherResponseDTO updateTeacher(UUID id, TeacherRequestDTO requestDTO) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Teacher not found"));

        teacher.setName(requestDTO.getName());
        teacher.setImage(requestDTO.getImage());
        teacher.setWorkType(requestDTO.getWorkType());
        teacher.setAbout(requestDTO.getAbout());

        teacher = teacherRepository.save(teacher);
        return converter.convertToResponseDTO(teacher);
    }

    @Override
    public void deleteTeacher(UUID id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public TeacherResponseDTO getTeacherById(UUID id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Teacher not found"));
        return converter.convertToResponseDTO(teacher);
    }

    @Override
    public List<TeacherResponseDTO> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream()
                .map(converter::convertToResponseDTO)
                .collect(Collectors.toList());
    }
}

