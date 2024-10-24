package uni.project.fitness.config.mapper;
import org.springframework.stereotype.Component;
import uni.project.fitness.dto.response.*;
import uni.project.fitness.entity.*;
import uni.project.fitness.servise.interfaces.CourseService;
import uni.project.fitness.entity.enums.IconDescription;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class MyConverter {
    public CourseResponseDTO convertToResponseDTO(Course course) {
            return CourseResponseDTO.builder()
                    .id(course.getId())
                    .title(course.getTitle())
                    .subTitle(course.getSubTitle())
                    .description(course.getDescription())
                    .image(course.getImage())
                    .trailerVideo(course.getTrailerVideo())
                    .whatYouWillGet(course.getWhatYouWillGet().stream()
                            .map(IconDescription::getIconObject)
                            .collect(Collectors.toList()))
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
    public TrainingForCourseResponseDTO convertToTrainingForCourseResponseDTO(Training training) {
        return TrainingForCourseResponseDTO.builder()
                .id(training.getId())
                .title(training.getTitle())
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
    public TrainingForTeacherResponseDTO convertToTrainingForTeacherResponseDTO(Training training, boolean isAvailable) {
        return TrainingForTeacherResponseDTO.builder()
                .id(training.getId())
                .title(training.getTitle())
                .teacher(convertToResponseDTO(training.getTeacher()))
                .description(training.getDescription())
                .shortDescription(training.getShortDescription())
                .image(training.getImage())
                .video(training.getVideo())
                .importantInfo(training.getImportantInfo())
                .equipments(training.getEquipments())
                .musclesInvolved(training.getMusclesInvolved())
                .results(training.getResults())
                .isAvailable(isAvailable)
                .build();
    }

    public TeacherResponseDTO convertToResponseDTO(Teacher teacher) {
            if (teacher == null){
                return null;
            }
            return TeacherResponseDTO.builder()
                    .id(teacher.getId())
                    .name(teacher.getName())
                    .image(teacher.getImage())
                    .workType(teacher.getWorkType())
                    .about(teacher.getAbout())
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
            if (course == null){
                return null;
            } else {
                return CourseDTO.builder()
                        .id(course.getId())
                        .title(course.getTitle())
                        .build();
            }
    }

    public CategoryDTO convertToCategoryDTO(Category category) {
            return CategoryDTO.builder()
                    .id(category.getId())
                    .name(category.getName())
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
                .courseId(completedCourse.getId())
                .userId(completedCourse.getUser().getId())
                .build();
    }
    public NewsResponseDTO convertToResponseDTO(News news) {
        return new NewsResponseDTO(
                news.getId(),
                news.getTitle(),
                news.getSubTitle(),
                news.getDescription(),
                news.getMedia()
        );
    }
    public ProgressResponseDTO convertToResponseDTO(Progress progress) {
        return ProgressResponseDTO.builder()
                .id(progress.getId())
                .pictures(progress.getPictures())
                .chest(progress.getChest())
                .hips(progress.getHips())
                .waist(progress.getWaist())
                .weight(progress.getWeight())
                .userId(progress.getUser().getId())
                .date(progress.getCreatedDate().toLocalDate())
                .build();
    }

    public TopCategoryResponseDTO convertToTopCategoryResponseDTO(Category topCategory) {
        if (topCategory == null) {
            return null;
        }

        TopCategoryResponseDTO responseDTO = new TopCategoryResponseDTO();
        responseDTO.setId(topCategory.getId());
        responseDTO.setName(topCategory.getName());

        if (topCategory.getSubcategories() != null && !topCategory.getSubcategories().isEmpty()) {
            List<SubCategoryResponseDTO> subCategoryDTOs = topCategory.getSubcategories().stream()
                    .map(subCategory -> convertToSubCategoryResponseDTO(subCategory, false))
                    .collect(Collectors.toList());
            responseDTO.setSubcategories(subCategoryDTOs);
        }
        return responseDTO;
    }

    public SubCategoryResponseDTO convertToSubCategoryResponseDTO(Category subCategory, boolean isAvailable) {
        if (subCategory == null) {
            return null;
        }
        SubCategoryResponseDTO responseDTO = new SubCategoryResponseDTO();
        responseDTO.setId(subCategory.getId());
        responseDTO.setIsAvailable(isAvailable);
        responseDTO.setName(subCategory.getName());
        responseDTO.setImage(subCategory.getImage());
        responseDTO.setParentCategoryId(subCategory.getParentCategory().getId());

        if (subCategory.getCourses() != null && !subCategory.getCourses().isEmpty()) {
            List<CourseResponseDTO> courseDTOs = subCategory.getCourses().stream()
                    .map(this::convertToResponseDTO)
                    .collect(Collectors.toList());
            responseDTO.setCourses(courseDTOs);
        }

        return responseDTO;
    }

}



