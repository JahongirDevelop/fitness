package uni.project.fitness.servise;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uni.project.fitness.dto.request.TrainingRequestDTO;
import uni.project.fitness.dto.response.*;
import uni.project.fitness.entity.Course;
import uni.project.fitness.entity.Teacher;
import uni.project.fitness.entity.Training;
import uni.project.fitness.exception.DataNotFoundException;
import uni.project.fitness.repository.CourseRepository;
import uni.project.fitness.repository.TeacherRepository;
import uni.project.fitness.repository.TrainingRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TrainingService {
    private final TrainingRepository trainingRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    public TrainingResponseDTO createTraining(TrainingRequestDTO requestDTO) {
        Training training = Training.builder()
                .title(requestDTO.getTitle())
                .teacher(teacherRepository.findById(requestDTO.getTeacherId())
                        .orElseThrow(() -> new DataNotFoundException("Teacher not found")))
                .course(courseRepository.findById(requestDTO.getCourseId())
                        .orElseThrow(() -> new DataNotFoundException("Course not found")))
                .description(requestDTO.getDescription())
                .shortDescription(requestDTO.getShortDescription())
                .image(requestDTO.getImage())
                .video(requestDTO.getVideo())
                .importantInfo(requestDTO.getImportantInfo())
                .equipments(requestDTO.getEquipments())
                .musclesInvolved(requestDTO.getMusclesInvolved())
                .results(requestDTO.getResults())
                .build();
        training = trainingRepository.save(training);
        return convertToResponseDTO(training);
    }

    public TrainingResponseDTO updateTraining(UUID id, TrainingRequestDTO requestDTO) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Training not found"));
        training.setTitle(requestDTO.getTitle());
        training.setTeacher(teacherRepository.findById(requestDTO.getTeacherId())
                .orElseThrow(() -> new DataNotFoundException("Teacher not found")));
        training.setCourse(courseRepository.findById(requestDTO.getCourseId())
                .orElseThrow(() -> new DataNotFoundException("Course not found")));
        training.setDescription(requestDTO.getDescription());
        training.setShortDescription(requestDTO.getShortDescription());
        training.setImage(requestDTO.getImage());
        training.setVideo(requestDTO.getVideo());
        training.setImportantInfo(requestDTO.getImportantInfo());
        training.setEquipments(requestDTO.getEquipments());
        training.setMusclesInvolved(requestDTO.getMusclesInvolved());
        training.setResults(requestDTO.getResults());
        training = trainingRepository.save(training);
        return convertToResponseDTO(training);
    }

    public void deleteTraining(UUID id) {
        trainingRepository.deleteById(id);
    }

    public TrainingResponseDTO getTrainingById(UUID id) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Training not found"));
        return convertToResponseDTO(training);
    }

    public List<TrainingResponseDTO> getAllTrainings() {
        List<Training> trainings = trainingRepository.findAll();
        return trainings.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private TrainingResponseDTO convertToResponseDTO(Training training, boolean includeCourse) {
        TrainingResponseDTO.TrainingResponseDTOBuilder builder = TrainingResponseDTO.builder()
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
                .course(CourseDTO.builder().id(training.getCourse().getId()).title(training.getCourse().getTitle()).build())
                .results(training.getResults());


        return builder.build();
    }

    private CourseResponseDTO convertCourseToResponseDTO(Course course, boolean includeTrainings) {
        CourseResponseDTO builder = CourseResponseDTO.builder()
                .id(course.getId())
                .title(course.getTitle())
                .subTitle(course.getSubTitle())
                .description(course.getDescription())
                .image(course.getImage())
                .trailerVideo(course.getTrailerVideo())
                .price(course.getPrice())
                .whatYouWillGet(course.getWhatYouWillGet())
                .whatToExpects(course.getWhatToExpects())
                .purpose(course.getPurpose())
//                .icons(course.getIcons())
                .results(course.getResults())
                .trainings(Collections.singletonList(TrainingDTO.builder().id(course.getId()).title(course.getTitle()).build()))
                .category(CategoryDTO.builder().id(course.getCategory().getId()).name(course.getCategory().getName()).build()).build();

//        if (includeTrainings && course.getTrainings() != null) {
//            builder.trainings(course.getTrainings().stream()
//                    .map(training -> convertToResponseDTO(training, false)) // Include trainings without courses to avoid recursion
//                    .collect(Collectors.toList()));
//        }

         return builder;
    }


    private TrainingResponseDTO convertToResponseDTO(Training training) {
        return convertToResponseDTO(training, true);
    }

    private CourseResponseDTO convertCourseToResponseDTO(Course course) {
        return convertCourseToResponseDTO(course, true);
    }


    private TeacherResponseDTO convertTeacherToResponseDTO(Teacher teacher) {
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

    public List<TrainingResponseDTO> getTrainingsByCourse(UUID courseId) {
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new DataNotFoundException("Course not found with id: " + courseId));
        List<TrainingResponseDTO> responseDTOList = new ArrayList<>();
        for (Training training : trainingRepository.findByCourse(course)) {
            responseDTOList.add(convertToResponseDTO(training));
        }
        return responseDTOList;
    }
}
