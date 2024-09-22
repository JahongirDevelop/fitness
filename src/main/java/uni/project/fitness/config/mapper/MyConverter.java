package uni.project.fitness.config.mapper;

import org.springframework.stereotype.Component;
import uni.project.fitness.dto.response.*;
import uni.project.fitness.entity.*;
import uni.project.fitness.servise.interfaces.CourseService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MyConverter {
        public CategoryResponseDTO convertToResponseDTO(Category category) {

            List<CourseResponseDTO> courseResponseDTOs = category.getCourses().stream()
                    .map(this::convertToResponseDTO)
                    .collect(Collectors.toList());

            return CategoryResponseDTO.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .courses(courseResponseDTOs)
                    .build();
        }

        public CourseResponseDTO convertToResponseDTO(Course course) {
            return CourseResponseDTO.builder()
                    .id(course.getId())
                    .title(course.getTitle())
                    .subTitle(course.getSubTitle())
                    .description(course.getDescription())
                    .image(course.getImage())
                    .trailerVideo(course.getTrailerVideo())
                    .price(course.getPrice())
                    .whatYouWillGet(course.getWhatYouWillGet()) // Handling IconDescription
                    .whatToExpects(course.getWhatToExpects())
                    .purpose(course.getPurpose())
                    .results(course.getResults())
                    .category(convertToCategoryDTO(course.getCategory()))
                    .trainings(course.getTrainings() != null ?
                            course.getTrainings().stream()
                                    .map(this::convertToTrainingDTO)
                                    .collect(Collectors.toList())
                            : new ArrayList<>())
                    .build();
        }

        public TrainingResponseDTO convertToResponseDTO(Training training) {

            return TrainingResponseDTO.builder()
                    .id(training.getId())
                    .title(training.getTitle())
                    .teacher(convertToResponseDTO(training.getTeacher()))
                    .course(convertToCourseDTO(training.getCourse()))
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

        public TeacherResponseDTO convertToResponseDTO(Teacher teacher) {
            return TeacherResponseDTO.builder()
                    .id(teacher.getId())
                    .name(teacher.getName())
                    .image(teacher.getImage())
                    .workType(teacher.getWorkType())
                    .about(teacher.getAbout())
                    .build();
        }

        public CategoryResponseDTOForUser convertToResponseDTOForUser(Category category, UserEntity user) {

            List<UserCourseResponseDTO> userCourseResponseDTOs = category.getCourses().stream()
                    .map(course -> convertToUserCourseResponseDTO(course, user))
                    .collect(Collectors.toList());

            return CategoryResponseDTOForUser.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .courses(userCourseResponseDTOs)
                    .build();
        }

        private TrainingDTO convertToTrainingDTO(Training training) {
            return TrainingDTO.builder()
                    .id(training.getId())
                    .title(training.getTitle())
                    .description(training.getDescription())
                    .image(training.getImage())
                    .build();
        }

        private CourseDTO convertToCourseDTO(Course course) {
            return CourseDTO.builder()
                    .id(course.getId())
                    .title(course.getTitle())
                    .build();
        }

        public CategoryDTO convertToCategoryDTO(Category category) {
            return CategoryDTO.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build();
        }

        private UserCourseResponseDTO convertToUserCourseResponseDTO(Course course, UserEntity user) {
            return UserCourseResponseDTO.builder()
                    .id(course.getId())
                    .title(course.getTitle())
                    .subTitle(course.getSubTitle())
                    .description(course.getDescription())
                    .image(course.getImage())
                    .trailerVideo(course.getTrailerVideo())
                    .price(course.getPrice())
                    .trainings(course.getTrainings().stream()
                            .map(this::convertToTrainingDTO)
                            .collect(Collectors.toList()))
                    .whatYouWillGet(course.getWhatYouWillGet())
                    .whatToExpects(course.getWhatToExpects())
                    .purpose(course.getPurpose())
                    .results(course.getResults())
                    .category(convertToCategoryDTO(course.getCategory()))
                    .isAccessible(user.hasActiveSubscriptionForCourse(course))
                    .build();
        }

    public CompletedCourseDTO convertToCompletedCourseDTO(CompletedCourse completedCourse, CourseService courseService) {
        CourseResponseDTO courseResponseDTO = convertToResponseDTO(completedCourse.getCourse());
        return CompletedCourseDTO.builder()
                .courseResponseDTO(courseResponseDTO)
                .build();
    }

    public CompletedCourseResponseDTO convertToCompletedCourseResponseDTO(CompletedCourse completedCourse) {
        return CompletedCourseResponseDTO.builder()
                .id(completedCourse.getId())
                .courseId(completedCourse.getCourse().getId())
                .userId(completedCourse.getUser().getId())
                .build();
    }








}


